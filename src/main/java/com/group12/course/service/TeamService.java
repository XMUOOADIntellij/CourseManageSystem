package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.entity.Klass;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import com.group12.course.entity.strategy.Strategy;
import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.exception.InformationException;
import com.group12.course.exception.TeamInAuditingException;
import com.group12.course.exception.UnauthorizedOperationException;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Team service 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Service
public class TeamService {

    @Autowired
    TeamDao teamDao;

    @Autowired
    KlassDao klassDao;

    @Autowired
    TeamStrategyDao teamStrategyDao;

    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;

    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;

    @Autowired
    CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;

    @Autowired
    TeamOrStrategyDao teamOrStrategyDao;

    @Autowired
    TeamAndStrategyDao teamAndStrategyDao;

    private final int teamIsValid = 1;
    private final int teamIsInvalid = 2;
    private final int teamIsInAuditing = 0;

    /**
     * 添加某个队伍
     *
     * @param team 新加的队伍对象
     * @return 返回的队伍对象中包含新添加的对象的id
     * */
    public Team createTeam(Team team){
        Klass klass = klassDao.getKlass(team.getKlass().getId());
        if (klass==null){
            throw new InformationException("课程 id 不存在");
        }
        team.setKlassSerial(klass.getKlassSerial());
        return teamDao.addTeam(team);
    }

    /**
     * 根据传入的学生 id
     * 获取其所在的队伍（以队长身份或队员身份的都算）
     *
     * @param id 查询的学生id
     * @return 查询到的队伍对象
     *
     * 此 api 暂时不实现了
     * */
    public Team getTeamByStudentId(Long id){
        return new Team();
        //teamDao.getTeamByStudentId(id);
    }

    /**
     * 根据 id 获取队伍
     *
     * @param id 队伍id
     * @return 返回一个包含所有队员的队伍
     * */
    public Team getTeamByTeamId(Long id){
        return teamDao.getTeamById(id);
    }

    /**
     * 根据队伍id 删除小组
     *
     * @param teamId 队伍的id
     * @return 该 id 所在的队伍的id
     * */
    public int deleteTeamByTeamId(Long teamId){
        return teamDao.deleteTeamById(teamId);
    }

    /**
     * 这个方法用于给现有的队伍添加组员
     *
     * @param team 新的队伍
     * @return 返回新的队伍对象
     * */
    public Team addMember(Team team){
        Team returnTeam = teamDao.addTeamMembers(team);
        Boolean status = checkTeamValidation(team);
        if (!status){
            returnTeam.setStatus(teamIsInvalid);
            int i =teamDao.changeTeam(returnTeam);
            throw new InformationException("队伍不合法");
            // 记得此处提醒前端相关状态码为409
        }
        return returnTeam;
    }

    /**
     * 检查此时该队伍是否在审核中
     * 在审核中时不可对组员增删
     * 若在审核中则直接抛出异常
     * @throws TeamInAuditingException 队伍在审核中异常
     * @param team 待检查队伍
     */
    private void checkTeamStatus(Team team){
        team = teamDao.getTeamWithoutMembersById(team.getId());
        if (team.getStatus()==teamIsInAuditing){
            throw new TeamInAuditingException("队伍审核中，无法添加/删除组员");
        }
    }

    /**
     * 根据传入的学生 id 和课程 id
     * 获取其所在该课程下的队伍（以队长身份或队员身份的都算）
     *
     * @param id 查询的学生id
     * @return 查询到的队伍对象
     * */
    public Team getTeamByStudentIdAndCourseId(Long id,Long courseId){
        return teamDao.getTeamByStudentIdAndCourseId(id, courseId);
    }

    /**
     * 权限判断，只有该小组的成员才可以进行相关操作
     * 若不满足权限，则抛出异常
     * @throws UnauthorizedOperationException 未授权异常
     * @param student 操作学生
     * @param teamId 操作的队伍
     */
    public void authCheck(Student student,Long teamId){
        if (student==null||!teamDao.checkStudentIsInSpecialTeam(student.getId(),teamId)){
            throw new UnauthorizedOperationException("只有该小组成员才可进行该操作");
        }
    }

    /**
     * 检查队伍是否符合分组要求
     *
     * @param team 待检查队伍
     * @return 是否符合，符合返回 true
     */
    public Boolean checkTeamValidation(Team team){
        List<TeamStrategy> strategyList = teamStrategyDao.selectTeamStrategyByCourseId(team.getCourse().getId());
        if (strategyList==null||strategyList.isEmpty()){
            return true;
        }
        Boolean strategyCheck = true;
        for (TeamStrategy teamStrategy:strategyList) {
            Boolean status=false;
            List<Strategy> strategies = teamStrategy.getStrategyList();
            for (Strategy strategy:strategies) {
                switch (strategy.getStrategyType()){
                    case "MemberLimitStrategy":
                        status = memberLimitStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "TeamOrStrategy":
                        status = teamOrStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "ConflictCourseStrategy":
                        status = conflictCourseStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "CourseMemberLimitStrategy":
                        status = courseMemberLimitStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "TeamAndStrategy":
                        status = teamAndStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    default:
                        // 默认不存在的时候默认为对的了（不影响其余的）
                        status=true;
                        break;
                }
                // 只要某条不符合，整体就不符合，不必继续判断
                if (!status){
                    break;
                }
            }
            strategyCheck=status;
            if (!status){
                break;
            }
        }
        return strategyCheck;
    }

    /**
     * 将某个学生从队伍内移除
     *
     * @param student 将要移除的学生对象
     * @param team  要移除出的队伍
     * @return 删除数量
     * */
    public int deleteTeamMember(Team team,Student student){
        checkTeamStatus(team);
        int count = teamDao.deleteTeamMember(team,student);
        Team checkTeam = teamDao.getTeamById(team.getId());
        Boolean status = checkTeamValidation(checkTeam);
        if (!status){
            checkTeam.setStatus(teamIsInvalid);
            int i =teamDao.changeTeam(checkTeam);
            throw new InformationException("队伍不合法");
            // 记得此处提醒前端相关状态码为409
        }
        return count;
    }
}

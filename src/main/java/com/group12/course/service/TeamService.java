package com.group12.course.service;

import com.group12.course.dao.KlassDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.entity.Klass;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import com.group12.course.exception.InformationException;
import com.group12.course.exception.UnauthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param student 组员
     * @return 返回新的队伍对象
     * */
    public Team addMember(Team team,Student student){
        return teamDao.addTeamMembers(team,student);
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

    public void authCheck(Student student,Long teamId){
        if (student==null||!teamDao.checkStudentIsInSpecialTeam(student.getId(),teamId)){
            throw new UnauthorizedOperationException("只有该小组成员才可进行该操作");
        }
    }

    /**
     * 将某个学生从队伍内移除
     *
     * @param student 将要移除的学生对象
     * @param team  要移除出的队伍
     * @return 删除数量
     * */
    public int deleteTeamMember(Team team,Student student){
        return teamDao.deleteTeamMember(team,student);
    }
}

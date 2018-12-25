package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * Team dao 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class TeamDao {

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    StudentDao studentDao;

    public Integer getKlassLastTeamSerial(Long klassId){
        List<Team> teams = teamMapper.selectTeamByKlassId(klassId);
        if (teams==null){
            return 1;
        }
        else {
            teams.sort(new Comparator<Team>() {
                @Override
                public int compare(Team o1, Team o2) {
                    return o1.getTeamSerial().compareTo(o2.getTeamSerial());
                }
            });
            return teams.get(teams.size()-1).getTeamSerial()+1;
        }
    }

    /**
     * 检查队伍信息是否合法
     * 其中检查 course klass leader 是否为空
     *
     * @param team 传入待检查的队伍对象
     * @return 若均不为空，返回 true
     * 否则为 false
     * */
    public Boolean checkTeamValid(Team team){
        try {
            Long courseId = team.getCourse().getId();
            Long klassId = team.getKlass().getId();
            Long leaderId = team.getLeader().getId();
        }
        catch (NullPointerException e){
            // throw team invalid Exception
            System.out.println("team message invalid");
            return false;
        }
        return true;
    }

    /**
     * 检查传入的队伍的队长是否在队伍内
     * 若在队内的话会抛出异常
     *
     * @param team 传入待检查的队伍对象
     * @return 若不在，返回 true
     * 否则为 false
     * */
    public Boolean checkLeaderInTeam(Team team, Course course){
        if (checkStudentIsInTeam(team.getLeader(),course)){
            //throw leader already in team exception
            System.out.println("leader is already in a team \n");
            return false;
        }
        return true;
    }

    /**
     * 检查传入的学生是否在某个队伍内
     * 若在队内的话会抛出异常
     *
     * @param student 传入待检查的队伍对象
     * @return 若不在，返回 true
     * 否则为 false
     * */
    public Boolean checkStudentIsInTeam(Student student,Course course){
        Team team = getTeamByStudentIdAndCourseId(student.getId(),course.getId());
        if (team.getId()==null){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 检查传入的队伍的队员是否在队伍内
     * 若在队内的话会抛出异常
     *
     * @param team 传入待检查的队伍对象
     * @return 若不在，返回 true
     * 否则为 false
     * */
    public Boolean checkMembersInTeam(Team team){
        if (team.getMembers()==null){
            return true;
        }
        Iterator<Student> iterator = team.getMembers().iterator();
        while (iterator.hasNext()){
            Student member = iterator.next();
            if (checkStudentIsInTeam(team.getLeader(),team.getCourse())){
                // throw members already in team exception
                System.out.println("member \n"+ member + "\n is already in a team \n");
                return false;
            }
        }
        return true;
    }

    /**
     * 根据 id 获取队伍
     *
     * @param account 队伍id
     * @return 返回一个包含所有队员的队伍*/
    public Team getTeamById(Long account){
        Team team = teamMapper.selectTeamById(account);
        return getMembers(team);
    }

    /**
     * 根据队长 id 获取队伍对象
     *
     * @param id 队长的 id
     * @return 以该 id 为队长的队伍
     * */
    public List<Team> getTeamByLeaderId(Long id){
        return teamMapper.selectTeamByLeaderId(id);
    }

    /**
     * 根据队员 id 获取队伍id
     *
     * @param id 队员的id
     * @return 该 id 所在的队伍的id
     * */
    public List<Long> getTeamByMembersId(Long id){
        return teamMapper.selectTeamIdByMembersId(id);
    }

    /**
     * 根据传入的学生 id 和课程 id
     * 获取其所在该课程下的队伍（以队长身份或队员身份的都算）
     *
     * @param id 查询的学生id
     * @return 查询到的队伍对象
     * */
    public Team getTeamByStudentIdAndCourseId(Long id,Long courseId){
        List<Team> teams = getTeamByLeaderId(id);
        Team finalTeam = null;
        if (teams==null||teams.isEmpty()){
            // 此时以该学生作为队长的小队不存在
            List<Long> teamIdByMembers = getTeamByMembersId(id);
            // 此时以该学生作为队员的队伍不存在
            if (teamIdByMembers!=null&&teamIdByMembers.isEmpty()){
                Iterator<Long> iterator = teamIdByMembers.iterator();
                for (Long currentId: teamIdByMembers) {
                    Team teamByMember = teamMapper.selectTeamById(currentId);
                    if (teamByMember.getCourse().getId().equals(courseId)){
                        // 此时该学生所在的队伍就是期望的队伍
                        finalTeam=teamByMember;
                    }
                }
            }
        }
        else {
            Iterator<Team> iterator = teams.iterator();
            while (iterator.hasNext()){
                Team team = iterator.next();
                if (team.getCourse().getId().equals(courseId)){
                    // 此时该学生所在的队伍就是期望的队伍
                    finalTeam=team;
                }
            }
        }
        return finalTeam==null? new Team():getMembers(finalTeam);
    }

    /**
     * 根据传入的班级 id
     * 获取其所在该班级下的队伍
     *
     * @param klassId 查询的班级的id
     * @return 查询到的队伍对象
     * */
    public List<Team> listTeamByKlassId(Long klassId){
        return teamMapper.selectTeamByKlassId(klassId);
    }

    /**
     * 给传入的队伍对象添加组员
     *
     * @param team 传入的队伍对象
     * @return 队伍对象
     * */
    public Team getMembers(Team team){
        if (!checkTeamValid(team)){
            return new Team();
        }
        List<Student> members = teamMapper.selectTeamMembersByTeamId(team.getId());
        List<Student> realMembers = new ArrayList<>();
        Iterator<Student> iterator =members.iterator();
        while (iterator.hasNext()){
            Student member = iterator.next();
            Student temp = studentDao.getStudentById(member.getId());
            if (temp.getAccount()!=null){
                member=temp;
            }
            realMembers.add(member);
        }
        team.setMembers(realMembers);
        return team;
    }

    /**
     * 根据队伍id 删除小组
     *
     * @param teamId 队伍的id
     * @return 该 id 所在的队伍的id
     * */
    public int deleteTeamById(Long teamId){
        int deleteTeamCount=teamMapper.deleteTeam(teamId);
        if (deleteTeamCount==1){
            int deleteTeamMembersCount = teamMapper.deleteTeamMembers(teamId);
            return deleteTeamMembersCount;
        }
        return -1;
    }

    /**
     * 将某个学生从队伍内移除
     *
     * @param member 将要移除的学生对象
     * @param team  要移除出的队伍
     * @return 删除数量
     * */
    public int deleteTeamMember(Team team,Student member){
        return teamMapper.deleteTeamMembers(member.getId());
    }

    /**
     * 添加某个队伍
     *
     * @param team 新加的队伍对象
     * @return 返回的队伍对象中包含新添加的对象的id
     * */
    public Team addTeam(Team team){
//        if (!checkTeamValid(team)||!checkLeaderInTeam(team,team.getCourse())||!checkMembersInTeam(team)){
//            return new Team();
//        }
        team.setStatus(2);
        team.setTeamSerial(getKlassLastTeamSerial(team.getKlass().getId()));
        int addTeamCount=teamMapper.addTeam(team, team.getCourse().getId(),
                team.getKlass().getId(),team.getLeader().getId());
        if (addTeamCount==0){
            return new Team();
        }
        if (team.getMembers()==null){
            return team;
        }
        addNewTeamMembers(team,team.getLeader());
        Iterator<Student> members = team.getMembers().iterator();
        while (members.hasNext()){
            addNewTeamMembers(team,members.next());
        }
        teamMapper.addTeamIntoKlass(team.getId(),team.getKlass().getId());
        return team;
    }

    /**
     * 这个方法用于给一个新的队伍添加组员
     *
     * @param team 新的队伍
     * @param member 组员
     * @return 返回新的队伍对象
     * */
    public Team addNewTeamMembers(Team team,Student member){
        if (!checkTeamValid(team)){
            return new Team();
        }
        int temp=teamMapper.addTeamMembers(team.getId(),member.getId());
        if (temp==0){
            // throw insert error
            System.out.println("error insert team members:"+member.getId()+" at team:"+team.getId());
        }
        return team;
    }

    /**
     * 这个方法用于给现有的队伍添加组员
     *
     * @param team 新的队伍
     * @param member 组员
     * @return 返回新的队伍对象
     * */
    public Team addTeamMembers(Team team,Student member){
        team=teamMapper.selectTeamById(team.getId());
        List<Long> memberInTeams = teamMapper.selectTeamIdByMembersId(member.getId());
        for (Long id:memberInTeams) {
            Team tempTeam = getTeamById(id);
            if (tempTeam.getCourse().getId().equals(team.getCourse().getId())){
                // 学生已在该课程下组队
                return new Team();
            }
        }
        if (team.getId()==null){
            // 传入的队伍信息不足
            return new Team();
        }
        int temp=teamMapper.addTeamMembers(team.getId(),member.getId());
        if (temp==0){
            // throw insert error
            System.out.println("error insert team members:"+member.getId()+" at team:"+team.getId());
            return new Team();
        }
        return team;
    }

    /**
     * 这个方法用于给更改队伍状态
     *
     * @param team 传入的队伍
     * @return 修改数量
     * */
    public int changeTeam(Team team){
        return teamMapper.updateTeam(team);
    }
}

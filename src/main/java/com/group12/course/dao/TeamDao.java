package com.group12.course.dao;

import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;


/**
 * Team dao 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class TeamDao {

    @Autowired
    TeamMapper teamMapper;

    /*public Team checkStudentInTeam(Student student){
        Team team=getTeamByStudentId(student.getId());
    }*/

    public Boolean checkTeamValid(Team team){
        try {
            Long courseId = team.getCourse().getId();
            Long klassId = team.getKlass().getId();
            Long leaderId = team.getLeader().getId();
        }
        catch (NullPointerException e){
            //throw team invalid Exception
            System.out.println("team message invalid");
            return false;
        }
        return true;
    }

    public Boolean checkLeaderInTeam(Team team){
        Team leaderInTeam =getTeamByStudentId(team.getLeader().getId());
        if (leaderInTeam!=null){
            //throw leader already in team exception
            System.out.println("leader is already in a team \n"+leaderInTeam);
            return false;
        }
        return true;
    }

    public Boolean checkMembersInTeam(Team team){
        Iterator<Student> iterator = team.getMembers().iterator();
        while (iterator.hasNext()){
            Student member = iterator.next();
            Long memberInTeam = teamMapper.selectTeamIdByMembersId(member.getId());
            if (memberInTeam!=null){
                // throw members already in team exception
                System.out.println("member \n"+ member + "\n is already in a team \n"+memberInTeam);
                return false;
            }
        }
        return true;
    }

    public Team getTeamById(Long account){
        return teamMapper.selectTeamById(account);
    }

    public Team getTeamByLeaderId(Long id){
        return teamMapper.selectTeamByLeaderId(id);
    }

    public Long getTeamByMembersId(Long id){
        return teamMapper.selectTeamIdByMembersId(id);
    }

    public int deleteTeamById(Long teamId){
        int deleteTeamCount=teamMapper.deleteTeam(teamId);
        if (deleteTeamCount==1){
            int deleteTeamMembersCount = teamMapper.deleteTeamMembers(teamId);
            return deleteTeamMembersCount;
        }
        return -1;
    }

    public int deleteTeamMember(Team team,Student member){
        return teamMapper.deleteTeamMembers(member.getId());
    }

    public Team addTeam(Team team){
        if (!checkTeamValid(team)||!checkLeaderInTeam(team)||!checkMembersInTeam(team)){
            return new Team();
        }
        team.setStatus(0);
        int addTeamCount=teamMapper.addTeam(team,team.getCourse().getId(),team.getKlass().getId(),team.getLeader().getId());
        if (addTeamCount==0){
            return new Team();
        }
        Iterator<Student> members = team.getMembers().iterator();
        while (members.hasNext()){
            addNewTeamMembers(team,members.next());
        }
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
        int temp=teamMapper.addTeamMembers(team.getId(),team.getCourse().getId(),team.getKlass().getId(),member.getId());
        if (temp==0){
            // throw insert error
            System.out.println("error insert team members:"+member.getId()+" at team:"+team.getId());
        }
        return team;
    }

    public Team addTeamMembers(Team team,Student member){
        team=teamMapper.selectTeamById(team.getId());
        if (!checkTeamValid(team)){
            // throw team not exist exception
            return new Team();
        }
        int temp=teamMapper.addTeamMembers(team.getId(),team.getCourse().getId(),team.getKlass().getId(),member.getId());
        if (temp==0){
            // throw insert error
            System.out.println("error insert team members:"+member.getId()+" at team:"+team.getId());
            return new Team();
        }
        return team;
    }

    public int changeTeam(Team team){
        return teamMapper.updateTeam(team);
    }

    public Team getTeamByStudentId(Long id){
        Team teamByLeader = getTeamByLeaderId(id);
        if (teamByLeader==null){
            Long teamIdByMembers = getTeamByMembersId(id);
            if (teamIdByMembers==null){
                return new Team();
            }
            else {
                Team teamByMember = teamMapper.selectTeamById(teamIdByMembers);

                return getMembers(teamByMember);
            }
        }
        else {
            return getMembers(teamByLeader);
        }
    }

    public Team getMembers(Team team){
        if (!checkTeamValid(team)){
            return new Team();
        }
        List<Student> members = teamMapper.selectTeamMembersByTeamId(team.getId());
        team.setMembers(members);
        return team;
    }
}

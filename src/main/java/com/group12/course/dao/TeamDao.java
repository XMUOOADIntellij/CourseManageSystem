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

    public Team getTeamById(Long account){
        return teamMapper.selectTeamById(account);
    }

    public Team getTeamByLeaderId(Long id){
        return teamMapper.selectTeamByLeaderId(id);
    }

    public Long getTeamByMembersId(Long id){
        return teamMapper.selectTeamIdByMembersId(id);
    }

    public int deleteTeam(Long account){
        return teamMapper.deleteTeam(account);
    }

    public int addTeam(Team team){
        Long courseId,klassId,leaderId;
        try {
            courseId = team.getCourse().getId();
            klassId = team.getKlass().getId();
            leaderId = team.getLeader().getId();
        }
        catch (NullPointerException e){
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            return 0;
        }
        team.setStatus(0);

        int addTeamCount=teamMapper.addTeam(team,courseId,klassId,leaderId);
        if (addTeamCount==0){
            return 0;
        }
        Iterator<Student> members = team.getMembers().iterator();
        while (members.hasNext()){
            addTeamMembers(team,members.next());
        }

        return addTeamCount;
    }

    public int addTeamMembers(Team team,Student member){
        Long courseId,klassId,teamId;
        courseId = team.getCourse().getId();
        klassId = team.getKlass().getId();
        teamId = team.getId();
        try {

        }
        catch (NullPointerException e){
            return 0;
        }
        int temp=teamMapper.addTeamMembers(teamId,courseId,klassId,member.getId());
        if (temp==0){
            System.out.println("error insert team members:"+member.getId()+" at team:"+team.getId());
        }
        return temp;
    }

    public int changeTeam(Team team){
        return teamMapper.updateTeam(team);
    }

    public Team getTeamStudentId(Long id){
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
        List<Student> members = teamMapper.selectTeamMembersByTeamId(team.getId());
        team.setMembers(members);
        return team;
    }
}

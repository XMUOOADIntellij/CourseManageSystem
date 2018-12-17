package com.group12.course.service;

import com.group12.course.dao.TeamDao;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
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

    public int createTeam(Team team){
        return teamDao.addTeam(team);
    }

    public Team getTeamByStudentId(Long id){
        return teamDao.getTeamByStudentId(id);
    }

    public Team getTeamByTeamId(Long id){
        return teamDao.getTeamById(id);
    }

    public int deleteTeamByTeamId(Long teamId){
        return teamDao.deleteTeamById(teamId);
    }

    public int addMember(Team team,Student student){
        return teamDao.addTeamMembers(team,student);
    }

    public int deleteTeamMember(Team team,Student student){
        return teamDao.deleteTeamMember(team,student);
    }
}

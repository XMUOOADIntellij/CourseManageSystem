package com.group12.course.dao;

import com.group12.course.entity.Team;
import com.group12.course.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
        return teamMapper.getTeam(account);
    }

    public int deleteTeam(Long account){
        return teamMapper.deleteTeam(account);
    }

    public int addTeam(Team team){
        return teamMapper.addTeam(team,team.getCourse().getId(),team.getKlass().getId(),team.getLeader().getId());
    }

    public int changeTeam(Team team){
        return teamMapper.updateTeam(team);
    }
}

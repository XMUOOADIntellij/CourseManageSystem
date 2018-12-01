package com.group12.course.dao.daoimpl;

import com.group12.course.dao.TeamDao;
import com.group12.course.entity.Team;
import com.group12.course.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年12月1日
 */
public class TeamDaoImpl implements TeamDao {

    @Autowired
    TeamMapper teamMapper;

    @Override
    public List<Team> listTeams() {
        return teamMapper.getAllTeams();
    }

    @Override
    public List<Team> getTeamsByClassId(int id) {
        return teamMapper.getTeamsByClassId(id);
    }

    @Override
    public Team getTeamByTeamId(int id) {
        return null;
    }

    @Override
    public boolean add(Team team) {
        return teamMapper.add(team);
    }

    @Override
    public boolean remove(int id) {
        return teamMapper.remove(id);
    }

    @Override
    public boolean update(Team team) {
        return teamMapper.update(team);
    }
}

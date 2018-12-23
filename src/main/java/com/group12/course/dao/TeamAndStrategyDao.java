package com.group12.course.dao;

import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.mapper.TeamAndStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamAndStrategyDao {

    @Autowired
    TeamAndStrategyMapper teamAndStrategyMapper;

    public TeamAndStrategy selectTeamAndStrategyById(Long id){
        return teamAndStrategyMapper.selectTeamAndStrategyById(id);
    }
}

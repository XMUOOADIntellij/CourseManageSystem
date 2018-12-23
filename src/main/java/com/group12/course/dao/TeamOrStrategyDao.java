package com.group12.course.dao;

import com.group12.course.entity.strategy.TeamOrStrategy;
import com.group12.course.mapper.TeamOrStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamOrStrategyDao {

    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;

    public TeamOrStrategy selectTeamOrStrategyById(Long id){
        return teamOrStrategyMapper.selectTeamOrStrategyById(id);
    }
}

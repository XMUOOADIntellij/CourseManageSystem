package com.group12.course.dao;

import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.mapper.TeamStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamStrategyDao {

    @Autowired
    TeamStrategyMapper teamStrategyMapper;

    public int addTeamStrategy(TeamStrategy teamStrategy){
        return teamStrategyMapper.addTeamStrategy(teamStrategy.getCourse().getId(),teamStrategy.getStrategy().getId(),teamStrategy.getStrategyName());
    }

    public TeamStrategy selectTeamStrategyByCourseIdAndStrategyName(Long courseId,String strategyName){
        return  teamStrategyMapper.selectTeamStrategyByCourseIdAndStrategyName(courseId,strategyName);
    }

    public TeamStrategy selectTeamStrategyByCourseId(Long courseId){
        return teamStrategyMapper.selectTeamStrategyByCourseId(courseId);
    }

}

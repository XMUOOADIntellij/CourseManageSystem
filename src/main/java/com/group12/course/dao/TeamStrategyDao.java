package com.group12.course.dao;

import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.mapper.TeamStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
@Component
public class TeamStrategyDao {

    @Autowired
    TeamStrategyMapper teamStrategyMapper;
    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;
    @Autowired
    CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;
    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;
    @Autowired
    TeamAndStrategyDao teamAndStrategyDao;
    @Autowired
    TeamOrStrategyDao teamOrStrategyDao;



    public int deleteTeamStrategyByCourseId(Long courseId){
        return teamStrategyMapper.deleteTeamStrategyByCourseId(courseId);
    }

    public int addTeamStrategy(TeamStrategy teamStrategy){

        List<TeamStrategy> teamStrategyList = teamStrategyMapper.selectTeamStrategyByCourseId(teamStrategy.getCourse().getId());
        Integer count = teamStrategyList.size();
        teamStrategy.setStrategySerial(count+1);

        return teamStrategyMapper.addTeamStrategy(teamStrategy.getCourse().getId(),teamStrategy.getStrategySerial(),teamStrategy.getStrategyName(),teamStrategy.getStrategyList().get(0).getId());
    }

    public TeamStrategy selectTeamStrategyByCourseIdAndStrategyName(Long courseId,String strategyName){
        return  teamStrategyMapper.selectTeamStrategyByCourseIdAndStrategyName(courseId,strategyName);
    }

    public List<TeamStrategy> selectTeamStrategyByCourseId(Long courseId){
        return teamStrategyMapper.selectTeamStrategyByCourseId(courseId);
    }

}

package com.group12.course.dao;

import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.mapper.TeamStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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


        //            List<ConflictCourseStrategy> conflictCourseStrategyList = conflictCourseStrategyDao.selectConflictCourseStrategyByCourseId(id);
        //删除该课程的冲突记录
//            int status2 = 1;
//            for (ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList) {
//                Long conflictCourseStrategyId = conflictCourseStrategy.getId();
//                status2 = conflictCourseStrategyDao.deleteConflictCourseStrategy(conflictCourseStrategyId)==0?0:status2;
//            }

        return teamStrategyMapper.deleteTeamStrategyByCourseId(courseId);
    }

    public int addTeamStrategy(TeamStrategy teamStrategy){
        return teamStrategyMapper.addTeamStrategy(teamStrategy);
    }

    public TeamStrategy selectTeamStrategyByCourseIdAndStrategyName(Long courseId,String strategyName){
        return  teamStrategyMapper.selectTeamStrategyByCourseIdAndStrategyName(courseId,strategyName);
    }

    public List<TeamStrategy> selectTeamStrategyByCourseId(Long courseId){
        return teamStrategyMapper.selectTeamStrategyByCourseId(courseId);
    }

}

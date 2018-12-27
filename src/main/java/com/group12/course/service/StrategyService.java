package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.entity.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService {

    @Autowired
    TeamStrategyDao teamStrategyDao;
    @Autowired
    TeamAndStrategyDao teamAndStrategyDao;
    @Autowired
    TeamOrStrategyDao teamOrStrategyDao;
    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;
    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;
    @Autowired
    CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;

    /**
     * 添加课程组队策略
     * @param teamStrategy
     * @return
     */
    public int addTeamStrategy(TeamStrategy teamStrategy){
        return teamStrategyDao .addTeamStrategy(teamStrategy);
    }

    /**
     * 添加与关系策略
     * @param teamAndStrategyList
     * @return
     */
    public List<TeamAndStrategy> addTeamAndStrategyList(List<TeamAndStrategy> teamAndStrategyList){
        return teamAndStrategyDao.addTeamAndStrategyList(teamAndStrategyList);
    }

    /**
     * 添加或关系策略
     * @param teamOrStrategyList
     * @return
     */
    public List<TeamOrStrategy> addTeamOrStrategyList(List<TeamOrStrategy> teamOrStrategyList){
        return teamOrStrategyDao.addTeamOrStrategyList(teamOrStrategyList);
    }


    /**
     * 添加组队人数限制策略
     * @param memberLimitStrategy
     * @return
     */
    public int addMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy){
        return memberLimitStrategyDao.addMemberLimitStrategy(memberLimitStrategy);
    }

    /**
     * 添加课程人数限制策略
     * @param courseMemberLimitStrategy
     * @return
     */
    public int addCourseMembetLimitStrategy(CourseMemberLimitStrategy courseMemberLimitStrategy){
        return courseMemberLimitStrategyDao.addCourseMemberLimitStrategy(courseMemberLimitStrategy);
    }

    /**
     * 添加冲突课程策略
     * @param conflictCourseStrategyList
     * @return
     */
    public List<ConflictCourseStrategy> addConflictCourseStrategyList(List<ConflictCourseStrategy> conflictCourseStrategyList){
        return conflictCourseStrategyDao.addConflictCourseStrategyList(conflictCourseStrategyList);
    }

    /**
     * 删除某课程的组队策略
     * @param courseId
     * @return
     */
    public int deleteTeamStrategyByCourseId(Long courseId){
        return teamStrategyDao.deleteTeamStrategyByCourseId(courseId);
    }

    public List<TeamStrategy> getTeamStrategyByCourseId(Long courseId){
        return teamStrategyDao.selectTeamStrategyByCourseId(courseId);
    }
}

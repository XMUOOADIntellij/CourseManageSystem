package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan Xue
 */
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
    @Autowired
    CourseDao courseDao;

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


    /**
     *  选修课人数限制策略之间是或关系时
     * @param strategyName
     * @param courseMemberLimitStrategyList
     * @return
     */
    public List<TeamOrStrategy> getTeamOrStrategyList(String strategyName,List<CourseMemberLimitStrategy> courseMemberLimitStrategyList){
        List<TeamOrStrategy> teamOrStrategyList = new ArrayList<>();
        for (CourseMemberLimitStrategy courseMemberLimitStrategy:courseMemberLimitStrategyList) {

            TeamOrStrategy teamOrStrategy = new TeamOrStrategy();
            teamOrStrategy.setStrategyName(strategyName);
            List<Strategy> strategyList = new ArrayList<>();
            strategyList.add(courseMemberLimitStrategy);
            teamOrStrategy.setStrategyList(strategyList);
            teamOrStrategyList.add(teamOrStrategy);
        }
        return teamOrStrategyList;
    }

    /**
     * 选修课程人数限制策略之间时与关系时
     * @param strategyName
     * @param courseMemberLimitStrategyList
     * @return
     */
    public List<TeamAndStrategy> getTeamAndStrategyList(String strategyName,List<CourseMemberLimitStrategy> courseMemberLimitStrategyList){
        List<TeamAndStrategy> teamAndStrategyList = new ArrayList<>();
        for (CourseMemberLimitStrategy courseMemberLimitStrategy:courseMemberLimitStrategyList) {
            TeamAndStrategy teamAndStrategy = new TeamAndStrategy();
            teamAndStrategy.setStrategyName(strategyName);
            List<Strategy> strategyList = new ArrayList<>(1);
            strategyList.add(courseMemberLimitStrategy);
            teamAndStrategy.setStrategyList(strategyList);
            teamAndStrategyList.add(teamAndStrategy);
        }
        return teamAndStrategyList;
    }

    /**
     * 选修课人数限制策略之间是或关系时
     * @param memberLimitStrategy
     * @param teamOrStrategyList
     * @return
     */
    public List<TeamAndStrategy> getTeamAndStrategyList1(MemberLimitStrategy memberLimitStrategy,List<TeamOrStrategy> teamOrStrategyList){
        List<TeamAndStrategy> teamAndStrategyList = new ArrayList<>();

        TeamAndStrategy teamAndStrategy1 = new TeamAndStrategy();
        teamAndStrategy1.setStrategyName(new String("MemberLimitStrategy"));
        List<Strategy> strategyList = new ArrayList<>();
        strategyList.add(memberLimitStrategy);
        teamAndStrategy1.setStrategyList(strategyList);
        teamAndStrategyList.add(teamAndStrategy1);

        TeamAndStrategy teamAndStrategy2 = new TeamAndStrategy();
        teamAndStrategy2.setStrategyName(new String("TeamOrStrategy"));
        List<Strategy> strategyList1 = new ArrayList<>();
        for (TeamOrStrategy teamOrStrategy : teamOrStrategyList) {
            strategyList1.add(teamOrStrategy);
        }
        teamAndStrategy2.setStrategyList(strategyList1);
        teamAndStrategyList.add(teamAndStrategy2);

        return teamAndStrategyList;
    }

    /**
     * 选修课人数限制策略之间是与关系时
     * @param memberLimitStrategy
     * @param teamAndStrategyList
     * @return
     */
    public List<TeamAndStrategy> getTeamAndStrategyList2(MemberLimitStrategy memberLimitStrategy,List<TeamAndStrategy> teamAndStrategyList){
        List<TeamAndStrategy> teamAndStrategyList2 = new ArrayList<>();

        TeamAndStrategy teamAndStrategy1 = new TeamAndStrategy();
        teamAndStrategy1.setStrategyName(new String("MemberLimitStrategy"));
        List<Strategy> strategyList = new ArrayList<>();
        strategyList.add(memberLimitStrategy);
        teamAndStrategy1.setStrategyList(strategyList);
        teamAndStrategyList2.add(teamAndStrategy1);

        TeamAndStrategy teamAndStrategy2 = new TeamAndStrategy();
        teamAndStrategy2.setStrategyName(new String("TeamAndStrategy"));
        List<Strategy> strategyList1 = new ArrayList<>();
        for (TeamAndStrategy teamAndStrategy : teamAndStrategyList) {
            strategyList1.add(teamAndStrategy);
        }
        teamAndStrategy2.setStrategyList(strategyList1);
        teamAndStrategyList2.add(teamAndStrategy2);

        return teamAndStrategyList2;
    }


    public TeamStrategy getTeamStrategy(Course course,List<TeamAndStrategy> teamAndStrategyList){
        TeamStrategy teamStrategy = new TeamStrategy();
        teamStrategy.setCourse(course);
        teamStrategy.setStrategyName(new String("TeamAndStrategy"));
        List<Strategy> strategyList2 = new ArrayList<>();
        for (TeamAndStrategy teamAndStrategy : teamAndStrategyList) {
            strategyList2.add(teamAndStrategy);
        }
        teamStrategy.setStrategyList(strategyList2);

        return teamStrategy;
    }

    public TeamStrategy getTeamStrategy(Course course,MemberLimitStrategy memberLimitStrategy){
        TeamStrategy teamStrategy = new TeamStrategy();
        teamStrategy.setCourse(course);
        teamStrategy.setStrategyName(new String("MemberLimitStrategy"));
        List<Strategy> strategyList = new ArrayList<>();
        strategyList.add(memberLimitStrategy);
        teamStrategy.setStrategyList(strategyList);

        return teamStrategy;
    }


    public List<ConflictCourseStrategy> getConflictCourseStrategyList(List<Long> conflictCourseList){
        List<ConflictCourseStrategy> conflictCourseStrategyList = new ArrayList<>();
        for (Long courseId : conflictCourseList) {
            ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy();
            conflictCourseStrategy.setCourse(courseDao.getCourse(courseId));
            conflictCourseStrategyList.add(conflictCourseStrategy);
        }
        return conflictCourseStrategyList;
    }

    public TeamStrategy getTeamStrategy2(Course course,List<ConflictCourseStrategy> conflictCourseStrategyList){
        TeamStrategy teamStrategy = new TeamStrategy();
        teamStrategy.setCourse(course);
        teamStrategy.setStrategyName("ConflictCourseStrategy");
        List<Strategy> strategyList = new ArrayList<>();
        for (ConflictCourseStrategy item : conflictCourseStrategyList) {
            strategyList.add(item);
        }
        teamStrategy.setStrategyList(strategyList);
        return teamStrategy;
    }

}

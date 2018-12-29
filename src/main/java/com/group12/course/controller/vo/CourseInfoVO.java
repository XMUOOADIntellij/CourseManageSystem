package com.group12.course.controller.vo;

import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.TeamStrategy;

import java.util.List;

/**
 * @author Tan Xue
 */
public class CourseInfoVO {

    private Course course;

    private List<TeamStrategy> teamStrategyList;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<TeamStrategy> getTeamStrategyList() {
        return teamStrategyList;
    }

    public void setTeamStrategyList(List<TeamStrategy> teamStrategyList) {
        this.teamStrategyList = teamStrategyList;
    }

    @Override
    public String toString() {
        return "CourseInfoVO{" +
                "course=" + course +
                ", teamStrategyList=" + teamStrategyList +
                '}';
    }
}

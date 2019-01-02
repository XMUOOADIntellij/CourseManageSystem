package com.group12.course.entity.strategy;

import com.group12.course.entity.Course;
import com.group12.course.entity.Team;

import java.io.Serializable;
import java.util.List;

/**
 * 小组在某课程下的组队策略
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class TeamStrategy  {
    
    private Course course;

    private Integer strategySerial;

    private String strategyName;

    private List<Strategy> strategyList;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getStrategySerial() {
        return strategySerial;
    }

    public void setStrategySerial(Integer strategySerial) {
        this.strategySerial = strategySerial;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public List<Strategy> getStrategyList() {
        return strategyList;
    }

    public void setStrategyList(List<Strategy> strategyList) {
        this.strategyList = strategyList;
    }

    @Override
    public String toString() {
        return "TeamStrategy{" +
                "course=" + course +
                ", strategySerial='" + strategySerial + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", strategyList=" + strategyList +
                '}';
    }
}

package com.group12.course.entity.strategy;

import com.group12.course.entity.Course;
import com.group12.course.entity.Team;

/**
 * 小组在某课程下的组队策略
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class TeamStrategy {
    Course course;

    String strategySerial;

    String strategyName;

    Strategy strategy;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStrategySerial() {
        return strategySerial;
    }

    public void setStrategySerial(String strategySerial) {
        this.strategySerial = strategySerial;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}

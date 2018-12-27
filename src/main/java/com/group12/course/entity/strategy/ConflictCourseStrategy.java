package com.group12.course.entity.strategy;


import com.group12.course.entity.Course;
import com.group12.course.entity.Team;

/**
 * 课程互相冲突不可组队策略
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class ConflictCourseStrategy extends Strategy {

    private Course course;

    public ConflictCourseStrategy() {
        this.strategyType="ConflictCourseStrategy";
    }

    public ConflictCourseStrategy(Long id, Course course) {
        super(id);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ConflictCourseStrategy{" +
                "course=" + course +
                '}';
    }
}

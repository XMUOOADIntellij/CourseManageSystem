package com.group12.course.entity.strategy;


import com.group12.course.entity.Course;

/**
 * 课程互相冲突不可组队策略
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class ConflictCourseStrategy extends Strategy {

    private Course courseFirst;

    private Course courseSecond;

    public ConflictCourseStrategy() {
    }

    public ConflictCourseStrategy(Long id, Course courseFirst, Course courseSecond) {
        super(id);
        this.courseFirst = courseFirst;
        this.courseSecond = courseSecond;
    }

    public Course getCourseFirst() {
        return courseFirst;
    }

    public void setCourseFirst(Course courseFirst) {
        this.courseFirst = courseFirst;
    }

    public Course getCourseSecond() {
        return courseSecond;
    }

    public void setCourseSecond(Course courseSecond) {
        this.courseSecond = courseSecond;
    }
}

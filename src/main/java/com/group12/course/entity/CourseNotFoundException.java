package com.group12.course.entity;

/**
 * @author Y Jiang
 * 2018/11/28
 */
public class CourseNotFoundException extends RuntimeException {
    private int courseId;
    public CourseNotFoundException(int courseId){ this.courseId=courseId; }
    public int getId() { return courseId; }
}

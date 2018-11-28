package com.group12.course.service;

import com.group12.course.entity.Course;

import java.util.List;

public interface CourseService {

    public Course getCourseNameById(int id);

    public Course addCourse(Course course);

    //删除课程
    public Course deleteCourse(int id);

    //更新课程
    public Course updateCourse(Course course);

    //以List形式,获取所有的课程
    public List<Course> getAllCourses();
}

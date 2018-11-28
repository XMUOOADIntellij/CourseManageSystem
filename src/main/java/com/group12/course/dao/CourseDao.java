package com.group12.course.dao;

import com.group12.course.entity.Course;
import java.util.List;


public interface CourseDao {

    // 获得所有课程
    public List<Course> getAllCourses();

    // 根据课程id获得课程
    public Course getCourseNameById(int id);


    // 添加课程
    public Course add(Course entity);

    // 删除课程
    public Course remove(int id);

    // 更新课程
    public Course update(Course entity);

}

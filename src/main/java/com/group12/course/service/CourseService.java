package com.group12.course.service;

import com.group12.course.entity.Course;

import java.util.List;

/**
 * @author Y Jiang
 * 2018/11/28
 * CourseService 接口
 * 获得所有课程
 * 根据id获取课程
 * 增加课程
 * 删除课程
 * 更新课程
 */

public interface CourseService {

    /**
     * 获得当前用户所有课程
     * @return List<Course>
     * @param teacherId 老师ID
     */
    List<Course> listCourses(Long teacherId);

    /**
     * 根据课程id获得课程
     * @param id int
     * @return Course
     */
    Course getCourseById(Long id);

    /**
     * 增加课程
     * @param course Course
     * @return Course
     */
    int addCourse(Course course);

    /**
     * 删除课程
     *
     * @param id int
     * @return Course
     */
    int deleteCourse(Long id);

    /**
     * 更新课程
     *
     * @param course Course
     * @return Course
     */
    int updateCourse(Course course);

}

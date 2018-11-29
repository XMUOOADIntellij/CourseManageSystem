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
     * 获得所有课程
     * @return List<Course>
     */
    List<Course> listCourses();

    /**
     * 根据课程id获得课程
     * @param id int
     * @return Course
     */
    Course getCourseNameById(int id);

    /**
     * 增加课程
     *
     * @param course Course
     * @return Course
     */
    Course addCourse(Course course);

    /**
     * 删除课程
     *
     * @param id int
     * @return Course
     */
    Course deleteCourse(int id);

    /**
     * 更新课程
     *
     * @param course Course
     * @return Course
     */
    Course updateCourse(Course course);


}

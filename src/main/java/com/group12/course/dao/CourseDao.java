package com.group12.course.dao;

import com.group12.course.entity.Course;
import java.util.List;


/**
 * @author Y Jiang
 * 2018/11/28
 * CourseDao  课程Dao接口
 * 获得所有课程
 * 根据id获取课程
 * 增加课程
 * 删除课程
 * 更新课程
 */
public interface CourseDao {

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
     * @param entity  Course
     * @return Course
     */
    Course add(Course entity);

    /**
     * 删除课程
     * @param id int
     * @return Course
     */
    Course remove(int id);

    /**
     * 更新课程
     * @param entity  Course
     * @return Course
     */
    Course update(Course entity);

}

package com.group12.course.mapper;

import com.group12.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Y Jiang
 * 2018/11/28
 *
 * CourseMapper 接口
 * 获得所有课程
 * 根据课程id获得课程
 * 添加课程
 * 删除课程
 * 更新课程
 */
@Mapper
@Component
public interface CourseMapper {

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
    Course getCourseNameById(@Param("id") int id);

    /**
     * 增加课程
     * @param entity  Course
     * @return Course
     */
    boolean add(Course entity);

    /**
     * 删除课程
     * @param id int
     * @return Course
     */
    boolean remove(int id);

    /**
     * 更新课程
     * @param entity  Course
     * @return Course
     */
    boolean update(Course entity);

}
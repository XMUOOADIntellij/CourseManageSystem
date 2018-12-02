package com.group12.course.dao;

import com.group12.course.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 *
 * CourseDao  课程Dao接口
 * @author Y Jiang
 * @date 2018/11/28
 */

@Component
public interface CourseDao {

    /**
     * 找到当前老師的所有课程
     * @param teacherId String
     * @return List  List<Course>
     */
    List<Course> listCourses(Long teacherId);

    /**
     * 通过课程ID 删除课程
     * @param id Integer
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入课程
     * @param record Course
     * @return int
     */
    int insert(Course record);

    /**
     * 插入课程 Selective
     * @param record Course
     * @return int
     */
    int insertSelective(Course record);

    /**
     * 根据课程Id查找单个课程
     * @param id Integer
     * @return Course
     */
    Course selectByPrimaryKey(Long id);

    /**
     * 更新课程 Selective
     * @param record Course
     * @return int
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * 更新课程
     * @param record Course
     * @return int
     */
    int updateByPrimaryKey(Course record);

}

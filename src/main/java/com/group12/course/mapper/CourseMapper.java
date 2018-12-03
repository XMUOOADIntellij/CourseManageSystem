package com.group12.course.mapper;

import com.group12.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Course Mapper 接口
 *
 * @author Y Jiang
 * @date 2018/12/1
 *
 */
@Mapper
@Component
public interface CourseMapper {

    /**
     * 找到当前用户的所有课程
     * @param teacherNum String
     * @return List  List<Course>
     */
    List<Course> listCourses(Long teacherNum);

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
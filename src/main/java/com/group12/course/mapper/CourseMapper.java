package com.group12.course.mapper;

import com.group12.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Course Mapper 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Mapper
@Component
public interface CourseMapper {

    /**
     * 删除课程
     * @param id
     * @return
     */
    int deleteCourse(Long id);

    /**
     * 添加课程
     * @param record
     * @return
     */
    int addCourse(Course record);

    /**
     * 根据课程id课程
     * @param id
     * @return
     */
    Course selectCourseById(Long id);

    /**
     * 选取所有的课程
     * @return
     */
    List<Course> selectAllCourse();

    /**
     * 修改课程
     * @param course
     * @return
     */
    int updateCourse(Course course);

    /**
     * 根据老师id查询课程
     * @param teacherId
     * @return
     */
    List<Course> selectCourseByTeacherId(Long teacherId);

    /**
     * 根据共享分组主课程查询从课程
     * @param teamMainCourseId
     * @return
     */
    List<Course> selectSubCourseByTeamMainCourseId(Long teamMainCourseId);

    /**
     * 根据共享taolunke主课程查询从课程
     * @param seminarMainCourseId
     * @return
     */
    List<Course> selectSubCourseBySeminarMainCourseId(Long seminarMainCourseId);

}
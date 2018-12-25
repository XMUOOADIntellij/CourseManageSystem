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

    int deleteCourse(Long id);

    int addCourse(@Param("teacherId") Long teacherId,@Param("record") Course record);

    Course selectCourseById(Long id);

    List<Course> selectAllCourse();

    int updateCourse(Course course);

    List<Course> selectCourseByTeacherId(Long teacherId);

    List<Course> selectSubCourseByTeamMainCourseId(Long teamMainCourseId);

    List<Course> selectSubCourseBySeminarMainCourseId(Long seminarMainCourseId);

}
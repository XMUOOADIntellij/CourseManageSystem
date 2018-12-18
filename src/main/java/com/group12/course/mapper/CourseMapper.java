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

    int addCourse(@Param("teacherId") Long teacherId,@Param("course") Course record);

    Course selectCourseById(Long id);

    int updateCourse(Course record);

    List<Course> selectCourseByTeacherId(Long teacherId);
}
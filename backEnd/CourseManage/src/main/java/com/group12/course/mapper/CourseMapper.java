package com.group12.course.mapper;

import com.group12.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CourseMapper {

    // 获得所有课程
    public List<Course> getAllCourses();

    // 根据课程id获得课程
    public Course getCourseNameById(@Param("id") int id);

    // 添加课程
    public Course add(Course entity);

    // 删除课程
    public Course remove(int id);

    // 更新课程
    public Course update(Course entity);

}
package com.group12.course.dao;

import com.group12.course.entity.Course;

import com.group12.course.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CourseDao {
    @Autowired
    private CourseMapper courseMapper;
    // 获得所有课程
    public List<Course> getAllCourses(){
       return courseMapper.getAllCourses();
    }

    // 根据课程id获得课程
    public Course getCourseNameById(int id) {
         return courseMapper.getCourseNameById(id);
    }


    // 添加课程
    public Course add(Course entity){
        return courseMapper.add(entity);
    }

    // 删除课程
    public Course remove(int id){
        return courseMapper.remove(id);
    }

    // 更新课程
    public Course update(Course entity){
        return courseMapper.update(entity);
    }

}

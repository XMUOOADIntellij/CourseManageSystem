package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Course Dao 层接口实现
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class CourseDao {

    @Autowired
    CourseMapper courseMapper;

    public Course getCourse(Long id){
        return courseMapper.getCourse(id);
    }

    public int deleteCourse(Long id){
        return courseMapper.deleteCourse(id);
    }

    public int addCourse(Course course){
        return courseMapper.addCourse(course);
    }

    public int updateCourse(Course course){
        return courseMapper.updateCourse(course);
    }

    public List<Course> listCourses(Long teacherId){
        return courseMapper.listCourses(teacherId);
    }
}

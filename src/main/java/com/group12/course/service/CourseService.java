package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klass Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class CourseService {

    @Autowired
    CourseDao courseDao;

    /**
     * 获得当前用户所有课程
     * @return List<Course>
     * @param  teacherId 老师ID
     */
    public List<Course> listCourses(Long teacherId){
        return courseDao.listCourses(teacherId);
    }

    /**
     * 根据课程id获得课程
     * @param id int
     * @return Course
     */
    public Course getCourseById(Long id){
        return courseDao.getCourse(id);
    }

    /**
     * 增加课程
     * @param course Course
     * @return Course
     */
    public int addCourse(Long teacherId,Course course){
        return courseDao.addCourse(teacherId,course);
    }

    /**
     * 删除课程
     *
     * @param id int
     * @return Course
     */
    public int deleteCourse(Long id){
        return courseDao.deleteCourse(id);
    }

    /**
     * 更新课程
     *
     * @param course Course
     * @return Course
     */
    public int updateCourse(Course course){
        return courseDao.updateCourse(course);
    }

}

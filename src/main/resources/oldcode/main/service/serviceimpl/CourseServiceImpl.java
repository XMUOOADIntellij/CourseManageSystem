package com.group12.course.service.serviceimpl;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Y Jiang
 * 2018/11/28
 */
@Service
public class CourseServiceImpl implements CourseService {

        @Autowired
        CourseDao courseDao;


        @Override
        public List<Course> listCourses(Long teacherNum){

            return courseDao.listCourses(teacherNum);
        }

        @Override
        public Course getCourseById(Long id) {
            return courseDao.selectByPrimaryKey(id);
        }

        @Override
        public int addCourse(Course course) {
            return courseDao.insert(course);
        }

        @Override
        public int deleteCourse(Long id) {
            return courseDao.deleteByPrimaryKey(id);
        }

        @Override
        public int updateCourse(Course course){
            return courseDao.updateByPrimaryKeySelective(course);
        }



}


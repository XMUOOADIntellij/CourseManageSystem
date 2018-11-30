package com.group12.course.service.serviceimpl;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Y Jiang
 * 2018/11/28
 */
@Service
public class CourseServiceImpl implements CourseService {

        @Autowired
        CourseDao courseDao;

        /**
         * 获得所有课程
         * @return List<Course>
         */
        @Override
        public List<Course> listCourses(){
                return courseDao.listCourses();
        }

        /**
         * 根据课程id获得课程
         * @param id int
         * @return Course
         */
        @Override
        public Course getCourseNameById(int id) {
            return courseDao.getCourseNameById(id);
        }

        /**
         * 增加课程
         * @param  course Course
         * @return Course
         */
        @Override
        public boolean addCourse(Course course) {
            return courseDao.add(course);

        }

        /**
         * 删除课程
         * @param id int
         * @return Course
         */
        @Override
        public boolean deleteCourse(int id) {
            return courseDao.remove(id);
        }

        /**
         * 更新课程
         * @param  course Course
         * @return Course
         */
        @Override
        public boolean updateCourse(Course course){
            return courseDao.update(course);
        }



}


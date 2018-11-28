package com.group12.course.service.serviceImpl;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

        @Autowired
        CourseDao courseDao;

        //通过id来获取课程的函数
        @Override
        public Course getCourseNameById(int id) {
            return courseDao.getCourseNameById(id);
        }

        //添加课程
        @Override
        public Course addCourse(Course course) {
            return courseDao.add(course);

        }

        //删除课程
        @Override
        public Course deleteCourse(int id) {
            return courseDao.remove(id);
        }

        //更新课程
        @Override
        public Course updateCourse(Course course){
//		    Course update=courses.get(id);
//		    update.setId(course.getId());
//            update.setName(course.getName());
//            update.setteacherName(course.getteacherName());
//            update.setLocation(course.getLocation());
//            return update;

            return courseDao.update(course);
        }

        //以List形式,获取所有的课程
        @Override
        public List<Course> getAllCourses(){
            return courseDao.getAllCourses();
        }


}


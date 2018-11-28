package com.group12.course.serviceImp;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

        @Autowired
        CourseDao courseDao;

        //这里用List存放数据,以避免数据库的连接操作
        //private List<Course> courses = new ArrayList<Course>();

        //由于可以使用增加课程来添加数据,这里不需要再默认添加数据了
        //public CourseService() {
            //courses.add(new Course("0","J2EE","邱明","海韵104"));
            //courses.add(new Course("1","OOAD","邱明","海韵205"));
            //courses.add(new Course("2", "软件工程", "王美红", "海韵208"));
        //}

        //一个简单的通过id来获取课程的函数
        public Course getCourseNameById(int id) {
            return courseDao.getCourseNameById(id);
        }

        //添加课程
        public Course addCourse(Course course) {
            return courseDao.add(course);

        }

        //删除课程
        public Course deleteCourse(int id) {
            return courseDao.remove(id);
        }

        //更新课程
        public Course updateCourse(Course course){
//		    Course update=courses.get(id);
//		    update.setId(course.getId());
//            update.setName(course.getName());
//            update.setTeacher_name(course.getTeacher_name());
//            update.setLocation(course.getLocation());
//            return update;

            return courseDao.update(course);
        }

        //以List形式,获取所有的课程
        public List<Course> getAllCourses(){
            return courseDao.getAllCourses();
        }


}


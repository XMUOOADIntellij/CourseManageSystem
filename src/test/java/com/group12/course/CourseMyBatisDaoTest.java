package com.group12.course;

import com.group12.course.entity.Course;
import com.group12.course.service.serviceimpl.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Y Jiang
 * 2018/11/28
 */

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CourseMyBatisDaoTest {

    @Autowired
    private CourseServiceImpl courseService;

    @Test
    @Rollback
    public void addCourseTest(){
        Course course = new Course();
        course.setId("3");
        course.setName("Test");
        course.setteacherName("TestTeacher");
        course.setLocation("TestLocation");
        System.out.println(courseService.addCourse(course).getName());
    }
}

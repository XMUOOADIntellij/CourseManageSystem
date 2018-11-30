package com.group12.course.Service;

import com.group12.course.entity.Course;
import com.group12.course.service.CourseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Y Jiang
 * @date 2018/11/28
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;
    private Course  testCourse;

    @Before
    public void initiateCourse(){
        testCourse = new Course();
        testCourse.setId("1000");
        testCourse.setName("Test");
        testCourse.setteacherName("TestTeacher");
        testCourse.setLocation("TestLocation");
    }

    @Test
    public void CourseCRUDTest(){
        Assert.assertTrue("Add Course Error",courseService.addCourse(testCourse));
        Assert.assertTrue("Get Course By Id Error",
                courseService.getCourseNameById(Integer.valueOf(testCourse.getId())).getName().equals("Test"));
        testCourse.setName("updateTest");
        Assert.assertTrue("Update Course Error",courseService.updateCourse(testCourse));
        Assert.assertTrue("Delete Course Error",courseService.deleteCourse(Integer.valueOf(testCourse.getId())));
    }

}

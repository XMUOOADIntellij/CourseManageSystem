package com.group12.course.Service;

import com.group12.course.entity.Course;
import com.group12.course.service.CourseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;


/**
 * @author Y Jiang
 * @date 2018/11/28
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CourseService courseService;
    private Course testCourse;

    @Before
    public void initialTestCourse(){
        testCourse = new Course();
        testCourse.setTeacherNum(new Long(1));
        testCourse.setCourseName("OOAD");
        testCourse.setIntroduction("OOAD的简介");
        testCourse.setPrePercentage(40);
        testCourse.setReportPercentage(30);
        testCourse.setQuesPercentage(30);
        testCourse.setTeamStartDate(LocalDateTime.of(2018, Month.DECEMBER,1,23,59,59));
        testCourse.setTeamEndDate(LocalDateTime.of(2018,Month.DECEMBER,6,23,59,59));
    }


    @Test
    public void testGetCourseById(){
        courseService.addCourse(testCourse);

        Assert.assertEquals(LocalDateTime.of(2018,Month.DECEMBER,1,23,59,59),
                courseService.getCourseById(testCourse.getId()).getTeamStartDate());
    }

    @Test
    public void testAddCourse(){
        Assert.assertEquals(1,courseService.addCourse(testCourse));
        Assert.assertEquals("OOAD",courseService.getCourseById(testCourse.getId()).getCourseName());
    }

    @Test
    public  void testDeleteCourse(){
        courseService.deleteCourse(testCourse.getId());
        Assert.assertEquals(0,courseService.deleteCourse(testCourse.getId()));
        courseService.addCourse(testCourse);
        Assert.assertEquals(1,courseService.deleteCourse(testCourse.getId()));
    }

    @Test
    public void testUpdateCourse(){

        courseService.addCourse(testCourse);

       testCourse.setQuesPercentage(20);
       testCourse.setCourseName(null);

       Assert.assertEquals(1,courseService.updateCourse(testCourse));
       Assert.assertEquals("OOAD",courseService.getCourseById(testCourse.getId()).getCourseName());
       Assert.assertEquals(20,courseService.getCourseById(testCourse.getId()).getQuesPercentage().intValue());
    }

    @Test
    public void testListCourses(){
        Assert.assertFalse(courseService.listCourses(testCourse.getTeacherNum()).isEmpty());
    }
}

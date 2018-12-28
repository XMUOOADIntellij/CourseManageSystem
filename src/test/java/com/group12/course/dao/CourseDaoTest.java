package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.Teacher;
import com.group12.course.mapper.CourseMapper;
import com.group12.course.mapper.TeacherMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseDaoTest {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeacherDao teacherDao;

    @Test
    public void testGetCourse(){
        Course record =  courseMapper.selectCourseById(new Long(16));
        Assert.assertNotNull(record.getId());
    }

    @Test
    public void testGetCourseByTeacherId(){
        List<Course> records = courseMapper.selectCourseByTeacherId(new Long(3));
        Assert.assertFalse(records.isEmpty());
    }


    @Test
    public void testGetSubCourseByTeamMainCourseId(){
        List<Course> courseList = courseMapper.selectSubCourseByTeamMainCourseId(new Long(16));
        Assert.assertFalse(courseList.isEmpty());
    }

    @Test
    public void testAddCourse(){
        Course course =new Course();
        Teacher teacher = teacherDao.getTeacher("2007100012");
        course.setTeacher(teacher);
        course.setCourseName("test");
        course.setPresentationPercentage(40);
        course.setQuestionPercentage(30);
        course.setReportPercentage(30);

        course.setTeamStartTime(LocalDateTime.of(2018,10,1,0,0,0));
        course.setTeamEndTime(LocalDateTime.of(2018,11,1,0,0,0));

        courseMapper.addCourse(course);

        System.out.print(course.getId());
        Assert.assertFalse(course.getId() == null);

    }

}

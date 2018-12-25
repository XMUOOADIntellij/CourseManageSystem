package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.mapper.CourseMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testGetCourse(){
        Course record =  courseMapper.selectCourseById(new Long(2));
        Assert.assertNotNull(record.getTeamMainCourse());
    }

    @Test
    public void testGetCourseByTeacherId(){
        List<Course> records = courseMapper.selectCourseByTeacherId(new Long(3));
        Assert.assertFalse(records.isEmpty());
    }


    @Test
    public void testGetSubCourseByTeamMainCourseId(){
        List<Course> courseList = courseMapper.selectSubCourseByTeamMainCourseId(new Long(1));
        Assert.assertFalse(courseList.isEmpty());
    }
}

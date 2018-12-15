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
    public void testSelectCourse(){
        Course record =  courseMapper.selectCourseById(new Long(1));
        Assert.assertNotNull(record.getCourseName());
    }
}

package com.group12.course;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import com.group12.course.mapper.CourseMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CourseTest {

    private CourseDao mapper;


 //根据id查询课
    @Test
 public void testGetCourseById() {
        Course course = this.mapper.getCourseNameById(2);
        System.out.println(course.getName());
 }
}

package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.mapper.CourseMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Y Jiang
 * @date  2018/11/30
 *
 * 测试Course的Dao层方法
 */

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CourseDaoTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    @Rollback
    public void testCourseDao(){
        Course course = new Course("1","Test","TestTeacher","TestLocation");
        courseMapper.remove(1);
        Assert.assertTrue("Add Course Error",courseMapper.add(course));


        course.setName("UpdateTest");
        Assert.assertTrue("Update Course Error",courseMapper.update(course));
        Assert.assertTrue("Get Course By Id Error",
                courseMapper.getCourseNameById(1).getName().equals(course.getName()));
        Assert.assertTrue("Delete Course Error",courseMapper.remove(Integer.valueOf(course.getId())));

    }




}

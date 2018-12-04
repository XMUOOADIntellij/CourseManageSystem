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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

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
    private Course course;

    @Before
    public void initialTestCourse(){
        course = new Course();
        //course.setId(new Long(1));
        course.setTeacherNum(new Long(1));
        course.setCourseName(new String("OOAD"));
        course.setIntroduction(new String("OOAD的简介"));
        course.setPrePercentage(new Integer(40));
        course.setReportPercentage(new Integer(30));
        course.setQuesPercentage(new Integer((30)));
        course.setTeamStartDate(LocalDateTime.of(2018, Month.DECEMBER,1,23,59,59));
        course.setTeamEndDate(LocalDateTime.of(2018,Month.DECEMBER,6,23,59,59));
    }

    @Test
    @Rollback
    public void testInsert(){
        Assert.assertEquals(1,courseMapper.insert(course));
    }


    @Test
    @Rollback
    public void testInsertSelective(){
        course.setCourseName(null);

        Assert.assertEquals(1,courseMapper.insertSelective(course));
        Assert.assertEquals(null,courseMapper.selectByPrimaryKey(course.getId()).getCourseName());

    }

    @Test
    @Rollback
    public  void testDeleteByPrimaryKey(){
        courseMapper.insert(course);
        Assert.assertEquals(1,courseMapper.deleteByPrimaryKey(course.getId()));
        Assert.assertEquals(0,courseMapper.deleteByPrimaryKey(course.getId()));

    }

    @Test
    @Rollback
    public  void testUpdateByPrimaryKey(){
        courseMapper.insert(course);
        course.setCourseName(null);
        course.setTeamStartDate(null);
        Assert.assertEquals(1,courseMapper.updateByPrimaryKey(course));
        Assert.assertEquals(null,courseMapper.selectByPrimaryKey(course.getId()).getCourseName());

    }

    @Test
    @Rollback
    public  void testUpdateByPrimaryKeySelective(){

        courseMapper.insert(course);
        course.setCourseName(null);
        course.setTeamStartDate(null);
        Assert.assertEquals(1,courseMapper.updateByPrimaryKeySelective(course));
        Assert.assertEquals("OOAD",courseMapper.selectByPrimaryKey(course.getId()).getCourseName());
    }

    @Test
    @Rollback
    public void testSelectByPrimaryKey(){
        courseMapper.insert(course);

        Assert.assertEquals(LocalDateTime.of(2018,Month.DECEMBER,1,23,59,59),
                courseMapper.selectByPrimaryKey(course.getId()).getTeamStartDate());
    }

}

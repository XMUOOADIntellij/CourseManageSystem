package com.group12.course.dao;


import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.mapper.CourseMapper;
import com.group12.course.mapper.TeamStrategyMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamStrategyDaoTest {

    @Autowired
    TeamStrategyMapper teamStrategyMapper;
    @Autowired
    CourseMapper courseMapper;

//    @Test
//    @Rollback
//    public void testAddTeamStrategy(){
//        TeamStrategy teamStrategy = new TeamStrategy();
//        Course course = courseMapper.selectCourseById(new Long(1));
//        ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy();
//
//    }


    @Test
    @Rollback
    public void testSelectTeamStrategyByCourseIdAndStrategyName(){
        TeamStrategy record = teamStrategyMapper.selectTeamStrategyByCourseIdAndStrategyName(new Long(1),new String("member_limit_strategy"));
        Assert.assertNotNull(record.getStrategy());
    }

}

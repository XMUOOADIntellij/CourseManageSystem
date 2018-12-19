package com.group12.course.dao;

import com.group12.course.entity.strategy.CourseMemberLimitStrategy;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.mapper.CourseMemberLimitStrategyMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseMemberLimitStrategyDaoTest {

    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    @Test
    public void testSelectMemberLimitStrategyById(){
        CourseMemberLimitStrategy record = courseMemberLimitStrategyMapper.selectCourseMemberLimitStrategyById(new Long(1));
        Assert.assertNotNull(record.getCourse());
    }
}

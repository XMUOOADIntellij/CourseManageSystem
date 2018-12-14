package com.group12.course.dao;

import com.group12.course.entity.Round;
import com.group12.course.mapper.RoundMapper;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoundDaoTest {
    @Autowired
    RoundMapper roundMapper;

    @Test
    public void testSelectRound(){
        Round record =  roundMapper.selectRoundById(new Long(1));
        Assert.assertNotNull(record.getCourse());
    }

}

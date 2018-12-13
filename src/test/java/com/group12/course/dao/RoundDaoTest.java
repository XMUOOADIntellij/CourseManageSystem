package com.group12.course.dao;

import com.group12.course.entity.Round;
import com.group12.course.mapper.RoundMapper;
import org.junit.Assert;

import org.junit.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
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

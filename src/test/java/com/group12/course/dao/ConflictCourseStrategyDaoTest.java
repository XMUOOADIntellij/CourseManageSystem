package com.group12.course.dao;

import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.mapper.ConflictCourseStrategyMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConflictCourseStrategyDaoTest {

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Test
    public void testselectConflictCourseStrategyByIdDao(){
        List<ConflictCourseStrategy> record = conflictCourseStrategyMapper.selectConflictCourseStrategyById(new Long(1));
        Assert.assertFalse(record.isEmpty());

    }

}

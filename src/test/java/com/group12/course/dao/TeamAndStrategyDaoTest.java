package com.group12.course.dao;

import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.mapper.TeamAndStrategyMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamAndStrategyDaoTest {

    @Autowired
    TeamAndStrategyMapper teamAndStrategyMapper;

    @Test
    public void testSelectTeamAndStrategyById(){
        List<TeamAndStrategy> recordList =  teamAndStrategyMapper.selectTeamAndStrategyById(new Long(1));
        for (TeamAndStrategy teamAndStrategy:recordList) {
            System.out.print(teamAndStrategy);
        }
        Assert.assertFalse(recordList.isEmpty());
    }

}

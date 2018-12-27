package com.group12.course.dao;

import com.group12.course.entity.strategy.TeamOrStrategy;
import com.group12.course.mapper.TeamOrStrategyMapper;
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
public class TeamOrStrategyDaoTest {

    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;
    @Autowired
    TeamOrStrategyDao teamOrStrategyDao;

    @Test
    public void testSelectTeamOrStrategyById(){
        List<TeamOrStrategy> recordList =  teamOrStrategyMapper.selectTeamOrStrategyById(new Long(1));
        for (TeamOrStrategy teamOrStrategy:recordList) {
            System.out.print(teamOrStrategy);
        }
        Assert.assertFalse(recordList.isEmpty());
    }



    @Test
    public void testCalculateId(){
        Long id = teamOrStrategyDao.calculateId();
        Assert.assertEquals(new Long(2),id);
    }


}

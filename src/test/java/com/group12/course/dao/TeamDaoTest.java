package com.group12.course.dao;


import com.group12.course.entity.Team;
import com.group12.course.mapper.TeamMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamDaoTest {

    @Autowired
    private TeamMapper teamMapper;

    @Test
    @Rollback
    public void testAddTeam(){
        Team testTeam = new Team();
        //testTeam.setCourseId(new Long(111));
        testTeam.setGmtCreate(new Date());
        testTeam.setGmtModified(new Date());
        //testTeam.setLeaderId(new Long(122));
        testTeam.setValid(true);
        testTeam.setLabel("aaa");
        testTeam.setName("asa");
        Assert.assertEquals("add Error",1,teamMapper.addTeam(testTeam));
    }
}

package com.group12.course.dao;

import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.mapper.TeamValidApplicationMapper;
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
public class TeamValidApplicationDaoTest {

    @Autowired
    private TeamValidApplicationMapper teamValidApplicationMapper;

    @Test
    @Rollback
    public void testGet(){
        TeamValidApplication teamValidApplication = teamValidApplicationMapper.selectTeamValidApplicationById(1L);
        System.out.println(teamValidApplication);
    }

    @Test
    @Rollback
    public void testAdd(){
        Team team = new Team();
        team.setId(7L);
        TeamValidApplication teamValidApplication =new TeamValidApplication(1,
                team,new Teacher(2L,"123456"),"i am ur father");
        int count=teamValidApplicationMapper.addTeamValidApplication(teamValidApplication);
        Assert.assertEquals(1,count);
    }

    @Test
    @Rollback
    public void testChange(){
        TeamValidApplication teamValidApplication = new TeamValidApplication();
        teamValidApplication.setId(1L);
        teamValidApplication.setStatus(1);
        Assert.assertEquals(1,teamValidApplicationMapper.updateTeamValidApplicationStatusById(teamValidApplication));
    }
}

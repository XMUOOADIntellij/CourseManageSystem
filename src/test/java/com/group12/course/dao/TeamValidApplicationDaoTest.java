package com.group12.course.dao;

import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.mapper.TeamValidApplicationMapper;
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
}

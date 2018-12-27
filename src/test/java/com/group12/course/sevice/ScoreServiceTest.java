package com.group12.course.sevice;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.RoundDao;
import com.group12.course.dao.ScoreDao;
import com.group12.course.entity.Course;
import com.group12.course.entity.Round;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.service.ScoreService;
import com.group12.course.service.TeamValidApplicationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreServiceTest {
    @Autowired
    ScoreService scoreService;
    @Autowired
    CourseDao courseDao;
    @Autowired
    RoundDao roundDao;
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    TeamValidApplicationService teamValidApplicationService;


    @Test
    public void testGetCourseScore() {
        //Assert.assertNotNull(scoreService.getCourseRoundScoreByTeacher(new Teacher(),new Long(1)));
    }

    @Test
    @Rollback
    public void test(){
        Team team = new Team();
        team.setId(3L);
        TeamValidApplication teamValidApplication =new TeamValidApplication();
        teamValidApplication.setStatus(2);
        teamValidApplication.setTeam(team);
        teamValidApplication.setReason("aaaa");
        int status = teamValidApplicationService.addApplication(teamValidApplication);
        System.out.println(status);
    }

}

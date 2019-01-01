package com.group12.course.sevice;

import com.group12.course.entity.Course;
import com.group12.course.entity.Klass;
import com.group12.course.entity.Team;
import com.group12.course.service.CourseService;
import com.group12.course.service.TeamService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    CourseService courseService;
    @Autowired
    TeamService teamService;

    @Test
    public void testChooseKlassByCourseAndTeam(){
        Team team = teamService.getTeamByTeamId(new Long(6));
        Course course = courseService.getCourseById(new Long(18));
        Klass klass = courseService.chooseKlassByCourseAndTeam(course,team);
        System.out.print(klass);
        Assert.assertNotNull(klass);
    }
}

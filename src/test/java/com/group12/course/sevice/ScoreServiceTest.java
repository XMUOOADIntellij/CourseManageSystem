package com.group12.course.sevice;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.RoundDao;
import com.group12.course.dao.ScoreDao;
import com.group12.course.entity.Course;
import com.group12.course.entity.Round;
import com.group12.course.entity.Teacher;
import com.group12.course.service.ScoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void testGetCourseScore() {
        //Assert.assertNotNull(scoreService.getCourseRoundScoreByTeacher(new Teacher(),new Long(1)));
    }

}

package com.group12.course.sevice;

import com.group12.course.entity.Course;
import com.group12.course.entity.Round;
import com.group12.course.entity.Seminar;
import com.group12.course.service.SeminarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    SeminarService seminarService;

    @Test
    public void testCreateSeminar(){
        Seminar seminar =new Seminar();
        seminar.setCourse(new Course(new Long(1)));
        seminar.setRound(new Round(new Long(88)));
        seminar.setMaxTeam(5);
        seminar.setSeminarName("test");
        seminar.setSeminarSerial(4);
        seminar.setVisible(false);

        seminarService.createSeminar(seminar);
    }

    @Test
    public void testDeleteSeminar(){
        seminarService.deleteSeminar(new Long(4));
    }

}

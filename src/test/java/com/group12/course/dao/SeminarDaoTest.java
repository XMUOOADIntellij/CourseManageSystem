package com.group12.course.dao;


import com.group12.course.entity.Course;
import com.group12.course.entity.Round;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.SeminarMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeminarDaoTest {
    @Autowired
    SeminarMapper seminarMapper;

    @Test
    public void testInsertSeminar(){
        Seminar seminar =new Seminar();
        seminar.setCourse(new Course(new Long(99)));
        System.out.println(seminar.getCourse().getId());
        seminar.setRound(new Round(new Long(88)));
        seminar.setMaxTeam(5);
        seminar.setSeminarName("test");
        seminar.setSeminarSerial(4);
        seminar.setVisible(false);
        seminarMapper.insertSeminar(seminar);
        System.out.println(seminar.getId());
    }

}

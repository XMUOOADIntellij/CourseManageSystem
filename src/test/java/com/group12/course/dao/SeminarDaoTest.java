package com.group12.course.dao;


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
    Seminar seminar;

    @Before
    public  void initial(){
        seminar = new Seminar();
    }
    @Test
    public void testInsertSeminar(){

    }
}

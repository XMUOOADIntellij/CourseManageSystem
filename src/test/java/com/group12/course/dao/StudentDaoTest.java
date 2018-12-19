package com.group12.course.dao;

import com.group12.course.entity.Student;
import com.group12.course.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentDaoTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentDao studentDao;

    @Test
    @Rollback
    public void testGetStudent(){
        Student student = studentDao.getStudentById(201L);
        System.out.println(student);
    }
}

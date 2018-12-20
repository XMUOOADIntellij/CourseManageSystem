package com.group12.course.dao;

import com.group12.course.entity.Teacher;
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
public class TeacherDaoTest {

    @Autowired
    private TeacherDao teacherDao;

    @Test
    @Rollback
    public void testGetTeacher(){
        Teacher teacher = teacherDao.getTeacher("213");
        System.out.println(teacher);
    }

    @Test
    @Rollback
    public void testActiveTeacher(){
        Teacher teacher = new Teacher("222","123456");
        teacher.setId(3L);
        int count=teacherDao.changeTeacher(teacher);
        System.out.println(count);
        System.out.println(teacherDao.getTeacher("222"));
    }
}

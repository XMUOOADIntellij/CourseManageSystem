package com.group12.course.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Tan Xue
 * 2018年12月1日
 *
 * 测试Class的Dao层方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClassDaoTest {

    @Autowired
    private Class1Mapper classMapper;

    @Test
    @Rollback
    public void testListClasses() {
        Class1 class1 = new Class1("1","test","周一上午三四节","公寓305");

    }

}

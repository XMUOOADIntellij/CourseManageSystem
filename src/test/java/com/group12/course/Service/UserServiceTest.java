package com.group12.course.Service;


import com.group12.course.entity.User;
import com.group12.course.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User Service 层测试
 * @author Xu Gang
 * @date 2018年12月1日
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private User testUser;

    @Before
    public void initTestUser(){
        testUser=new User("11111","123456");
        /**
         * TODO
         * 等待添加用户方可测试
         * */
    }

    @Test
    public void testCheckUser(){
        /**
         * TODO
         * */
    }

    @Test
    public void testChangePassword(){
        /**
         * TODO
         * */
    }

    @After
    public void removeTestUser(){
        /**
         * TODO
         * 待移除用户方可测试*/
    }
}

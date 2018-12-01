package com.group12.course.Service;


import com.group12.course.entity.User;
import com.group12.course.service.UserService;
import org.junit.After;
import org.junit.Assert;
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
        userService.addUser(testUser);
    }

    @Test
    public void testCheckUser(){
        Assert.assertEquals("checkUser Error",testUser,
                userService.checkUser(testUser.getAccount(),testUser.getPassword()));
    }

    @Test
    public void testChangePassword(){
        String newPassword=new String("9876543");
        testUser.setPassword(newPassword);
        Assert.assertEquals("changePassword Error",1,
                userService.changePassword(testUser));
    }

    @After
    public void removeTestUser(){
        userService.deleteUser(testUser.getAccount());
    }
}

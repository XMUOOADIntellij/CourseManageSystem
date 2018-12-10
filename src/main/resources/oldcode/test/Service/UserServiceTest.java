package com.group12.course.Service;


import com.group12.course.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User Service 层测试
 * @author Xu Gang
 * @date 2018年12月1日
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private  UserService userService;


    @Test
    public void testCheckUser(){
        User testUser =new User();
        testUser.setAccount("243");
        testUser.setPassword("123");
        userService.addUser(testUser);
        Assert.assertEquals("checkUser Error",testUser.getAccount(),
                userService.checkUser(testUser.getAccount(),testUser.getPassword()).getAccount());
        userService.deleteUser(testUser.getAccount());
    }

    @Test
    public void testChangePassword(){
        User testUser =new User();
        testUser.setAccount("243");
        testUser.setPassword("123");
        userService.addUser(testUser);

        String newPassword=new String("9876543");
        testUser.setPassword(newPassword);
        Assert.assertEquals("changePassword Error",1,
                userService.changePassword(testUser));

        userService.deleteUser(testUser.getAccount());
    }

    @Test
    public void testGetVerificationCode(){
        User testUser =new User();
        testUser.setAccount("243");
        testUser.setPassword("123");

        Assert.assertEquals("error","email is empty"
                ,userService.getVerificationCode(testUser));
        testUser.setEmail("277030573@qq.com");

        String code=userService.getVerificationCode(testUser);
        System.out.println(code);

        userService.deleteUser(testUser.getAccount());
    }
}

package com.group12.course.dao;

import com.group12.course.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试 User 的 DAO 层方法
 * @author Xu Gang
 * @date 2018年12月1日
 * */

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void testgetUser(){
        User testUser =new User();
        testUser.setAccount("24320162202934");
        testUser.setPassword("123456");
        testUser.setEmail("277030573@qq.com");
        testUser.setName("xg");
        Assert.assertEquals("Get User By account Error",
                testUser.getAccount(),
                userMapper.getUserByAccount(testUser.getAccount()).getAccount());
    }

    @Test
    @Rollback
    public void testupdateUser(){
        User testUser =new User();
        testUser.setAccount("24320162202934");
        testUser.setPassword("123456");
        testUser.setEmail("277030573@qq.com");
        testUser.setName("xg");
        testUser.setPassword("456789");
        Assert.assertTrue("Set User Password By account Error",
                1 == userMapper.updateUser(testUser));
        Assert.assertTrue("Set User Password By account failed, password",
                userMapper.getUserByAccount(testUser.getAccount()).getPassword().equals(testUser.getPassword()));

    }

    @Test
    @Rollback
    public void testAddUser(){
        User testUser =new User();
        testUser.setAccount("243");
        testUser.setPassword("123");
        testUser.setEmail("277@qq.com");
        testUser.setName("xgg");
        Assert.assertEquals("insert Error",1,userMapper.addUser(testUser));
    }

    @Test
    @Rollback
    public void testDeleteUser(){
        User testUser =new User();
        testUser.setAccount("243");
        testUser.setPassword("123");
        testUser.setEmail("277@qq.com");
        testUser.setName("xgg");
        userMapper.addUser(testUser);
        Assert.assertEquals("delete error",1,userMapper.deleteUser(testUser.getAccount()));
        Assert.assertEquals("delete error",0,userMapper.deleteUser("1234"));
    }

}

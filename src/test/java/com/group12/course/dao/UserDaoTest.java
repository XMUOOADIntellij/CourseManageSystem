package com.group12.course.dao;

import com.group12.course.entity.User;
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
    public void testUserDaoGetUserByAccount(){
        User testUser =new User("24320162202934","123456","277030573@qq.com","xg");
        Assert.assertEquals("Get User By account Error",
                testUser.getAccount(),
                userMapper.getUserByAccout(testUser.getAccount()).getAccount());
    }

    @Test
    @Rollback
    public void testUserDaoSetUserPasswordByAccount(){
        User testUser =new User("24320162202934","123456","277030573@qq.com","xg");
        testUser.setPassword("456789");
        Assert.assertTrue("Set User Password By account Error",
                userMapper.setUserPasswordByAccount(testUser.getAccount(),testUser.getPassword()));
        Assert.assertTrue("Set User Password By account failed, password",
                userMapper.getUserByAccout(testUser.getAccount()).getPassword().equals(testUser.getPassword()));

    }

}

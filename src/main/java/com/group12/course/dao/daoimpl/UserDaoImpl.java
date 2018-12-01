package com.group12.course.dao.daoimpl;

import com.group12.course.dao.UserDao;
import com.group12.course.entity.User;
import com.group12.course.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User dao 层对应接口的实现
 * @author Xu Gang
 * @date 2018年11月29日
 */

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccout(account);
    }

    @Override
    public Boolean setUserPasswordByAccount(User user){
        return userMapper.setUserPasswordByAccount(user.getAccount(),user.getPassword());
    }
}
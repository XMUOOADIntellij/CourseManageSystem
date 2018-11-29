package com.group12.course.service.serviceimpl;

import com.group12.course.dao.UserDao;
import com.group12.course.entity.User;
import com.group12.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User service 层对应接口的实现
 * @author Xu Gang
 * @date 2018年11月29日
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }
}

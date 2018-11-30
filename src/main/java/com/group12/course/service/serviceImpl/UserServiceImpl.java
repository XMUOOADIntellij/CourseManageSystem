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

    /**
     * 通过账户，判断密码是否正确，获取用户对象
     * @param account password 用户账户，用户输入的密码
     * @return User 若用户输入正确，则返回正确的用户对象，
     * 若用户输入错误，则返回一个临时生成的用户对象
     */
    @Override
    public User checkUser(String account,String password) {
        User user = userDao.getUserByAccount(account);
        if (user.getPassword().equals(password)){
            return user;
        }
        else{
            return new User("0","0");
        }
    }

    /**
     * 通过传入的用户信息，修改密码
     * @param user 用户
     * @return 若修改成功，返回 true，失败则 false
     */
    @Override
    public Boolean changePassword(User user){
        return userDao.setUserPasswordByAccount(user);
    }
}

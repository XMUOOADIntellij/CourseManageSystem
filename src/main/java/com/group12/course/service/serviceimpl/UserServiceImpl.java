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
        User user = userDao.getUser(account);
        // 若该用户在数据库中不存在，则 user == null，要剔除这种情况
        if ( user != null && user.getPassword().equals(password)){
            return user;
        }
        else{
            return new User();
        }
    }

    /**
     * 通过传入的用户信息，修改密码
     * @param user 用户
     * @return 若修改成功，返回 true，失败则 false
     */
    @Override
    public int changePassword(User user){
        if (user.getPassword()==null||user.getPassword()==null){
            return 0;
        }
        else {
            return userDao.updateUser(user);
        }
    }

    /**
     * 添加用户
     * @param user 新用户对象
     * @return 添加数量（0代表失败，1代表成功）
     * */
    @Override
    public int addUser(User user){
        if(user.getAccount()==null||user.getPassword()==null){
            return 0;
        }
        else {
            return userDao.addUser(user);
        }
    }

    /**
     * 删除用户
     * @param account 被删除账号
     * @return 删除数量（0代表删除失败）
     * */
    @Override
    public int deleteUser(String account){
        if (account==null){
            return 0;
        }
        else {
            return userDao.deleteUser(account);
        }
    }

    /**
     * 修改用户
     * @param user 用户 包含邮箱
     * @return 返回修改的数量
     * */
    @Override
    public int changeEmail(User user){
        if(user.getAccount()==null||user.getEmail()==null){
            return 0;
        }
        else {
            return userDao.updateUser(user);
        }
    }

    /**
     * 修改姓名
     * @param user 用户信息 包含姓名
     * @return 返回修改的数量
     * */
    @Override
    public int changeName(User user){
        if(user.getAccount()==null||user.getName()==null){
            return 0;
        }
        else {
            return userDao.updateUser(user);
        }
    }

    /**
     * 激活用户
     * @param user 用户信息
     * @return 返回激活数量
     * */
    @Override
    public int changeActiveStatus(User user){
        if(user.getAccount()==null){
            return 0;
        }
        else {
            user.setActive(true);
            return userDao.updateUser(user);
        }
    }
}

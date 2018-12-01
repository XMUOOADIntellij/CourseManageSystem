package com.group12.course.service;

import com.group12.course.entity.User;

/**
 * User service 层对应接口
 * @author Xu Gang
 * @date 2018年11月29日
 */

public interface UserService {

    /**
     * 通过账户，判断密码是否正确，获取用户对象
     * @param account 用户账户
     * @param password 用户输入的密码
     * @return User 若用户输入正确，则返回正确的用户对象，
     * 若用户输入错误，则返回一个临时生成的用户对象
     */
    User checkUser(String account,String password);

    /**
     * 通过传入的用户信息，修改密码
     * @param user 用户
     * @return 若修改成功，返回 true，失败则 false
     */
    int changePassword(User user);

    int addUser(User user);

    /*
    * TODO
    * 目前只做了登陆部分，其他部分还没做
    */
}

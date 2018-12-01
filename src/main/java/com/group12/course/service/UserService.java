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
     * */
    User checkUser(String account,String password);

    /**
     * 通过传入的用户信息，修改密码
     * @param user 用户
     * @return 返回修改数量（0代表修改失败）
     * */
    int changePassword(User user);

    /**
     * 添加用户
     * @param user 新用户对象
     * @return 添加数量（0代表失败，1代表成功）
     * */
    int addUser(User user);

    /**
     * 删除用户
     * @param account 被删除账号
     * @return 删除数量（0代表删除失败）
     * */
    int deleteUser(String account);
    /*
    * TODO
    * 目前只做了登陆部分，其他部分还没做
    */
}

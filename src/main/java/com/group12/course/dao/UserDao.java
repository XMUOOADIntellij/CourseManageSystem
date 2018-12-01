package com.group12.course.dao;

import com.group12.course.entity.User;

/**
 * User dao 层对应接口
 * @author Xu Gang
 * @date 2018年11月29日
 */

public interface UserDao {

    /**
     * 获取用户密码
     * @param account 用户账户
     * @return User 用户
     */
    User getUserByAccount(String account);

    /**
     * 修改用户密码
     * @param user 用户账户对象
     * @return user 返回修改操作后的用户
     */
    int setUserPasswordByAccount(User user);

    /**
     * 添加用户
     * @param user 用户对象
     * @return 添加个数
     * */
    int addUser(User user);

    /**
     * 删除用户
     * @param account 被删除的用户账号
     * @return 删除个数
     * */
    int deleteUser(String account);

    /**
      * 其余等待实现
      */
}

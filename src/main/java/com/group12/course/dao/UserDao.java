package com.group12.course.dao;

import com.group12.course.entity.User;

/*
 * User dao 层对应接口
 * @author Xu Gang
 * @date 2018年11月29日
 */

public interface UserDao {

    /*
     * 获取用户密码
     * @return User用户
     */

    public User getUserByAccount(String account);

    /*
    *
    * 其余等待实现
    * */
}

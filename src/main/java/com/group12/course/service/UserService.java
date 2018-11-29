package com.group12.course.service;

import com.group12.course.entity.User;

/**
 * User service 层对应接口
 * @author Xu Gang
 * @date 2018年11月29日
 */

public interface UserService {

    /**
     * 通过账户获取用户对象
     * @param account 用户账户
     * @return User
     */
    User getUserByAccount(String account);

    /*
    * TODO
    * 目前只做了登陆部分，其他部分还没做
    */
}

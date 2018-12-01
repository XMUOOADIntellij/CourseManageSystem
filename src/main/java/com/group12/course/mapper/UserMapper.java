package com.group12.course.mapper;

import com.group12.course.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * User Mapper 层对应接口
 * @author Xu Gang
 * @date 2018年11月29日
 */

@Mapper
@Component
public interface UserMapper {

    /**
    * 通过账号获取用户信息
    * @param account 用户账号
    * @return User对象
    * */
    User getUserByAccout(String account);

    /**
     * 判断用户密码是否正确
     * @param account 用户账号
     * @param password 用户输入的账号
     * @return 修改个数
     * */
    int setUserPasswordByAccount(String account,String password);

    /**
     * 添加用户
     * @param user 用户
     * @return 添加个数
     * */
    int addUser(User user);

    /**
     * 删除用户
     * @param account 被删除的用户账号
     * @return 删除个数*
     * */
    int deleteUser(String account);
}

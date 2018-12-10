
package com.group12.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * User Mapper 层对应接口
 * @author Xu Gang
 * @date 2018年12月2日
 */

@Mapper
@Component
public interface UserMapper {

    /**
     * 通过账号获取用户信息
     * @param account 用户账号
     * @return User对象
     * */
    User getUserByAccount(String account);

    /**
     * 修改用户信息
     * @param record 用户
     * @return 修改个数
     * */
    int updateUser(User record);

    /**
     * 添加用户
     * @param record 用户
     * @return 添加个数
     * */
    int addUser(User record);

    /**
     * 删除用户
     * @param account 被删除的用户账号
     * @return 删除个数*
     * */
    int deleteUser(String account);
}

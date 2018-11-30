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
    */
    User getUserByAccout(String account);

    /**
     * 判断用户密码是否正确
     * @param account 用户账号
     * @param password 用户输入的账号
     * @return User对象
     */
    Boolean setUserPasswordByAccount(String account,String password);
}

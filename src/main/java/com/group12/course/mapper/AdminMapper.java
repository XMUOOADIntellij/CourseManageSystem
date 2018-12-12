package com.group12.course.mapper;

import com.group12.course.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Admin Mapper
 * @author Xu Gang
 * @date 2018年12月12日
 * */

@Mapper
@Component
public interface AdminMapper {

    /**
     * 通过 account 获取 Admin 对象
     *
     * @param account admin 账号
     * @return 数据库中获取到的 Admin 对象
     * */
    Admin getAdminByAccount(String account);

}
package com.group12.course.dao;

import com.group12.course.entity.Admin;
import com.group12.course.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Admin Dao 层对应接口实现
 * @author Xu Gang
 * @date 2018年12月12日
 * */
@Component
public class AdminDao {

    @Autowired
    AdminMapper adminMapper;

    public Admin login(Admin admin){
        if (admin==null||admin.getAccount()==null||admin.getPassword()==null){
            // 传入数据有误
            return new Admin();
        }
        else {
            Admin returnAdmin = adminMapper.getAdminByAccount(admin.getAccount());
            if (returnAdmin==null){
                // returnAdmin 为空代表未找到该账户
                return new Admin();
            }
            else if (returnAdmin.getPassword().equals(admin.getPassword())){
                return returnAdmin;
            }
            else {
                // 密码错误
                return new Admin();
            }
        }
    }
}

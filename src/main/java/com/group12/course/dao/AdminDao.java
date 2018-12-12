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

    /**
     * 登陆，验证密码是否正确
     * @param admin 传入的 admin 对象
     * @return 若密码正确，返回数据库中的 admin对象
     * 若密码错误，返回一个空对象
     * */
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

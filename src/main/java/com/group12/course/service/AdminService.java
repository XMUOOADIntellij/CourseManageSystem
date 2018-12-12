package com.group12.course.service;

import com.group12.course.dao.AdminDao;
import com.group12.course.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Admin Service
 * @author Xu Gang
 * @date 2018年12月12日
 * */
@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    /**
     * 登陆
     * @param admin 传入的 admin 对象
     * @return 返回 Dao 层返回的对象
     * */
    public Admin login(Admin admin){
        return adminDao.login(admin);
    }
}

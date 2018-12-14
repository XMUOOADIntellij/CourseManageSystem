package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.group12.course.entity.Admin;
import com.group12.course.tools.Jwt;
import com.group12.course.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Admin controller
 * @author Xu Gang
 * @date 2018年12月11日
 * */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    final Long tokenLifeCycle = 60L* 1000L* 60L * 2L;

    /**
     * admin 登陆
     *
     * @param admin 前端传入的用户对象
     * 若登陆成功，返回 200，admin id，admin account，和 jwt token
     * 若登陆失败，返回 400
     * */
    @PostMapping(value = "/login",produces = "application/json; charset=utf-8")
    public void login(@RequestBody Admin admin, HttpServletResponse response)throws IOException {
        Admin returnAdmin=adminService.login(admin);
        if (returnAdmin.getPassword()==null){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
            String token = Jwt.sign(returnAdmin,tokenLifeCycle);
            Map map = new HashMap(4);
            map.put("id",returnAdmin.getId());
            map.put("account",returnAdmin.getAccount());
            map.put("jwt",token);
            String json = JSON.toJSONString(map);
            response.getWriter().write(json);
        }
    }


}

package com.group12.course.controller;

import com.group12.course.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin controller
 * @author Xu Gang
 * @date 2018年12月11日
 * */

@RestController
@RequestMapping("/admin")
public class AdminController {

   /* @Autowired
    AdminService adminService;*/
   /*TODO*/

    @PostMapping(value = "/login",produces = "application/json; charset=utf-8")
    public void login(@RequestBody Teacher user, HttpServletResponse response)throws IOException {
        /*TODO*/
    }


}

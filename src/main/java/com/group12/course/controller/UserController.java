package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.group12.course.entity.Jwt;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.service.StudentService;
import com.group12.course.service.TeacherService;
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
 * User Controller
 * @author Xu Gang
 * @date 2018年12月11日
 * */

@RestController

@RequestMapping("/user")
public class UserController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    public void login(@RequestBody Teacher user, HttpServletResponse response)throws IOException {

        final int studentAccountLength=14;
        if (user.getAccount().length()>=studentAccountLength){
            Student tempStudent=new Student();
            tempStudent.setAccount(user.getAccount());
            tempStudent.setPassword(user.getPassword());
            Student returnStudent = studentService.login(tempStudent);
            if (returnStudent.getAccount()==null){
                response.setStatus(400);
            }
            else{
                String token = Jwt.sign(returnStudent,60L* 1000L* 30L);
                response.setStatus(200);
                Map map = new HashMap(1);
                map.put("jwt",token);
                String param= JSON.toJSONString(map);
                String obj = JSON.toJSONString(returnStudent);
                response.getWriter().write(obj);
                response.getWriter().write(param);
            }
        }
        else {
            Teacher returnTeacher = teacherService.login(user);
            if (returnTeacher.getAccount()==null){
                response.setStatus(400);
            }
            else{
                String token = Jwt.sign(returnTeacher,60L* 1000L* 30L);
                response.setStatus(200);
                Map map = new HashMap(1);
                map.put("jwt",token);
                String param= JSON.toJSONString(map);
                String obj = JSON.toJSONString(returnTeacher);
                response.getWriter().write(obj);
                response.getWriter().write(param);
            }
        }
    }


}

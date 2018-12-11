package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.internal.org.bouncycastle.asn1.ocsp.ResponseData;
import com.group12.course.entity.JWT;
import com.group12.course.entity.Student;
import com.group12.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController

@RequestMapping("/user")
public class StudentController {

    @Autowired
    StudentService studentService;

    /*@PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    public void login(@RequestBody Student student, HttpServletResponse response)throws IOException {
        Student returnStudent = studentService.login(student);
        if (returnStudent.getAccount()==null){
            response.setStatus(400);
        }
        else{
            String token = JWT.sign(returnStudent,60L* 1000L* 30L);
            response.setStatus(200);
            Map map = new HashMap(1);
            map.put("jwt",token);
            String param= JSON.toJSONString(map);
            String obj = JSON.toJSONString(returnStudent);
            response.getWriter().write(obj);
            response.getWriter().write(param);
        }
    }*/
}

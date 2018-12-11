package com.group12.course.controller;

import com.group12.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Student Controller 层
 * @author Xu Gang
 * @date 2018年12月11日
 * */
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

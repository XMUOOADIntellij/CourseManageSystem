package com.group12.course.controller;

import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Student Controller 层
 * @author Xu Gang
 * @date 2018年12月11日
 * */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "",produces = "application/json; charset=utf-8")
    public List<Student> getAllStudent(@RequestBody Student student, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new ArrayList();
    }

    @GetMapping(value = "/searchstudent",produces = "application/json; charset=utf-8")
    public Student searchStudent(@RequestBody Student student, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Student();
    }

    @PutMapping(value = "/{studentId}/information",produces = "application/json; charset=utf-8")
    public void changeInfomation(@RequestBody Student student, HttpServletResponse response)throws IOException {
        /*TODO*/
    }

    @PutMapping(value = "/{studentId}/password",produces = "application/json; charset=utf-8")
    public void changePassword(@RequestBody Student student, HttpServletResponse response)throws IOException {
        /*TODO*/
    }

    @DeleteMapping(value = "/{studentId}",produces = "application/json; charset=utf-8")
    public void deleteStudent(@RequestBody Student student, HttpServletResponse response)throws IOException {
        /*TODO*/
    }
}

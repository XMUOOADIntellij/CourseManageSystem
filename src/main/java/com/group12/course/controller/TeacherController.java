package com.group12.course.controller;

import com.group12.course.entity.Teacher;
import com.group12.course.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Teacher Controller 层
 * @author Xu Gang
 * @date 2018年12月11日
 * */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping(value = "",produces = "application/json; charset=utf-8")
    public void createTeacher(@RequestBody Teacher teacher,@PathVariable String teacherId, HttpServletResponse response)throws IOException {
        /*TODO*/
    }

    @GetMapping(value = "",produces = "application/json; charset=utf-8")
    public Teacher getTeacher(@RequestBody Teacher teacher,@PathVariable String teacherId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Teacher();
    }

    @GetMapping(value = "/searchTeacher",produces = "application/json; charset=utf-8")
    public Teacher searchTeacher(@RequestBody Teacher teacher, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Teacher();
    }

    @PutMapping(value = "/{teacherId}/information",produces = "application/json; charset=utf-8")
    public void changeInfomation(@RequestBody Teacher teacher,@PathVariable String teacherId, HttpServletResponse response)throws IOException {
        /*TODO*/
    }

    @PutMapping(value = "/{teacherId}/password",produces = "application/json; charset=utf-8")
    public void changePassword(@RequestBody Teacher teacher, HttpServletResponse response)throws IOException {
        /*TODO*/
    }

    @DeleteMapping(value = "/{teacherId}",produces = "application/json; charset=utf-8")
    public void deleteTeacher(@RequestBody Teacher teacher, HttpServletResponse response)throws IOException {
        /*TODO*/
    }
}

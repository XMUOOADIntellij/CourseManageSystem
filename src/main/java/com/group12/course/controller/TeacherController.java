package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.group12.course.entity.Teacher;
import com.group12.course.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void createTeacher(@RequestBody Teacher teacher, HttpServletResponse response)throws IOException {
        int count=teacherService.addTeacher(teacher);
        if (count==0){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
        }
    }

    @GetMapping(value = "",produces = "application/json; charset=utf-8")
    public List<Teacher> getAllTeacher(@RequestBody Teacher teacher, HttpServletResponse response)throws IOException {
        List<Teacher> list=teacherService.getAllTeacher();
        if (list.isEmpty()){
            response.setStatus(400);
            return new ArrayList<>();
        }
        else {
            response.setStatus(200);
            return list;
        }
    }

    @GetMapping(value = "/searchTeacher",produces = "application/json; charset=utf-8")
    public List<Teacher> searchTeacher(@RequestParam(value = "identity") String param, HttpServletResponse response)throws IOException {
        List<Teacher> list=teacherService.getTeacherByParam(param);
        if (list.isEmpty()){
            response.setStatus(404);
            return new ArrayList<>();
        }
        else {
            response.setStatus(200);
            return list;
        }
    }

    @PutMapping(value = "/{teacherId}/information",produces = "application/json; charset=utf-8")
    public void changeInfomation(@RequestBody Teacher teacher,@PathVariable Long teacherId, HttpServletResponse response)throws IOException {
        teacher.setId(teacherId);
        int count= teacherService.updateTeacher(teacher);
        if (count ==0){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
            Map map = new HashMap(6);
            map.put("id",teacher.getId());
            map.put("account",teacher.getAccount());
            map.put("name",teacher.getTeacherName()==null?"":teacher.getTeacherName());
            map.put("email",teacher.getEmail()==null?"":teacher.getEmail());
            String json = JSON.toJSONString(map);
            response.getWriter().write(json);
        }
    }

    @PutMapping(value = "/{teacherId}/password",produces = "application/json; charset=utf-8")
    public void resetPassword(@PathVariable Long teacherId,HttpServletResponse response)throws IOException {
        int count = teacherService.resetPassword(teacherId);
        if (count==0){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
        }
    }

    @DeleteMapping(value = "/{teacherId}",produces = "application/json; charset=utf-8")
    public void deleteTeacher(@PathVariable Long teacherId, HttpServletResponse response)throws IOException {
        int count = teacherService.deleteTeacherById(teacherId);
        if (count==0){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
        }
    }
}

package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.group12.course.entity.Teacher;
import com.group12.course.service.TeacherService;
import com.group12.course.tools.Jwt;
import com.group12.course.controller.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 创建教师
     *
     * @param teacher 前端传入的用户对象
     * 若激活成功，返回 200
     * 若激活失败，返回 400
     * */
    @PostMapping(value = "",produces = "application/json; charset=utf-8")
    public void createTeacher(@RequestBody Teacher teacher, HttpServletResponse response){
        int count=teacherService.addTeacher(teacher);
        if (count==0){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
        }
    }

    /**
     * 获取全部教师
     *
     *
     * 若教师表有对象，返回 200 和对象列表
     * 若没有教师，返回 400
     * */
    @GetMapping(value = "",produces = "application/json; charset=utf-8")
    public List<Teacher> getAllTeacher(HttpServletResponse response) {
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

    /**
     * 根据账号或者姓名获取教师
     *
     *
     * 若教师表有该对象，返回 200 和对象列表
     * 若没有教师，返回 400
     * */
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

    /**
     * 根据 id 修改教师对象
     *
     *
     * 若成功修改，返回 200 和修改后的
     * 若修改失败，返回 400
     * */
    @PutMapping(value = "/{teacherId}/information",produces = "application/json; charset=utf-8")
    public void changeInformation(@RequestBody TeacherVO teacherVO, @PathVariable Long teacherId, HttpServletResponse response)throws IOException {
        Teacher teacher=new Teacher(teacherVO);
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

    /**
     * 重置教师密码
     *
     *
     * 若成功重置，返回 200
     * 若重置失败，返回 404
     * */
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

    /**
     * 删除教师
     *
     *
     * 若教师表有该对象，删除并返回 200
     * 若没有该教师，返回 404
     * */
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

    /**
     * 用户激活
     *
     * @param user 前端传入的用户对象
     * 若激活成功，返回 200
     * 若激活失败，返回 400
     * */
    @PutMapping(value = "/active",produces = "application/json; charset=utf-8")
    public void active(@RequestBody Teacher user, HttpServletRequest request, HttpServletResponse response)throws IOException {
        int modifyCount=0;
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if (jwtTeacher!=null){
            Teacher tempTeacher=new Teacher(jwtTeacher.getAccount());
            tempTeacher.setId(jwtTeacher.getId());
            tempTeacher.setPassword(user.getPassword());
            tempTeacher.setEmail(user.getEmail());
            tempTeacher.setActive(true);
            System.out.println(tempTeacher);
            modifyCount = teacherService.updateTeacher(tempTeacher);
        }
        else {
            System.out.println("null");
        }
        if (modifyCount==0){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
        }
    }
}

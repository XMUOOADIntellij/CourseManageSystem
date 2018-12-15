package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.group12.course.tools.Jwt;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.service.StudentService;
import com.group12.course.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private final Long tokenLifeCycle = 60L* 1000L* 60L * 2L;
    private final int studentAccountLength=14;

    /**
     * 用户登陆
     *
     * @param user 前端传入的用户对象
     * 若登陆成功，返回 200
     * 若登陆失败，返回 400
     * */
    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    public void login(@RequestBody Teacher user, HttpServletResponse response)throws IOException {
        // 用于存储返回的数据的临时对象
        Teacher temp=new Teacher();
        Boolean isTeacher = false;
        String token="";

        // 区分传入的是学生还是教师，调用不同的 Service
        if (user.getAccount().length()>=studentAccountLength){
            Student tempStudent=new Student(user.getAccount(),user.getPassword());
            Student returnStudent = studentService.login(tempStudent);
            if (returnStudent.getAccount()!=null){
                temp.setId(returnStudent.getId());
                temp.setAccount(returnStudent.getAccount());
                temp.setTeacherName(returnStudent.getStudentName());
                isTeacher=false;
                token = Jwt.sign(returnStudent,tokenLifeCycle);
            }
        }
        else {
            Teacher returnTeacher = teacherService.login(user);
            if (returnTeacher.getAccount() != null) {
                token = Jwt.sign(returnTeacher, tokenLifeCycle);
                temp.setId(returnTeacher.getId());
                temp.setAccount(returnTeacher.getAccount());
                temp.setTeacherName(returnTeacher.getTeacherName());
                isTeacher = true;
                token = Jwt.sign(returnTeacher, tokenLifeCycle);
            }
        }

        // 若登陆失败的话 temp 不会有成员被赋值
        if (temp.getAccount()==null){
            response.setStatus(400);
        }
        else{
            response.setStatus(200);
            Map map = new HashMap(6);
            map.put("id",temp.getId());
            map.put("account",temp.getAccount());
            map.put("role",isTeacher?"teacher":"student");
            map.put("name",temp.getTeacherName());
            map.put("isActive",temp.getActive()==null?false:temp.getActive());
            map.put("jwt",token);
            String json = JSON.toJSONString(map);
            response.getWriter().write(json);
        }
    }


    /**
     * 获取用户信息
     *
     * @param request 请求头中存储着 jwt 信息
     * 若激活成功，返回 200
     * 若激活失败，返回 400
     * */
    @GetMapping(value = "/information",produces = "application/json; charset=utf-8")
    public void information(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        // 区分传入的是学生还是教师，调用不同的 Service
        if (jwtStudent!=null){
            Student student=studentService.getStudent(jwtStudent.getAccount());
            if (student==null||student.getAccount()==null){
                response.setStatus(400);
            }
            else {
                response.setStatus(200);
                Map map = new HashMap(6);
                map.put("id",student.getId());
                map.put("account",student.getAccount());
                map.put("role","student");
                map.put("name",student.getStudentName());
                map.put("email",student.getEmail());
                String json = JSON.toJSONString(map);
                response.getWriter().write(json);
            }
        }
        else if (jwtTeacher!=null){
            Teacher teacher=teacherService.getTeacher(jwtTeacher.getAccount());
            if (teacher==null||teacher.getAccount()==null){
                response.setStatus(400);
            }
            else {
                response.setStatus(200);
                Map map = new HashMap(6);
                map.put("id",teacher.getId());
                map.put("account",teacher.getAccount());
                map.put("role","teacher");
                map.put("name",teacher.getTeacherName());
                map.put("email",teacher.getEmail());
                String json = JSON.toJSONString(map);
                response.getWriter().write(json);
            }
        }
        else {
            response.setStatus(400);
        }
    }

    /**
     * 修改密码
     *
     * @param request 请求头中存储着 jwt 信息
     * @param user 前端请求的 json 映射成的对象，只包修改后的 password
     * 若修改成功，返回 200
     * 若修改失败，返回 400
     * */
    @PutMapping(value = "/password",produces = "application/json; charset=utf-8")
    public void password(@RequestBody Teacher user,HttpServletRequest request, HttpServletResponse response)throws IOException {
        int modifyCount=0;
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        // 区分传入的是学生还是教师，调用不同的 Service
        if (jwtStudent!=null){
            Student tempStudent=new Student(jwtStudent.getAccount());
            tempStudent.setPassword(user.getPassword());
            modifyCount = studentService.updateStudent(tempStudent);
        }
        else {
            Teacher tempTeacher=new Teacher(jwtTeacher.getAccount());
            tempTeacher.setPassword(user.getPassword());
            modifyCount = teacherService.updateTeacher(tempTeacher);
        }
        if (modifyCount==0){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
        }
    }

    /**
     * 忘记密码，将密码发至邮箱
     *
     * @param account 忘记密码的账号
     * 若发送邮件成功，返回 200
     * 若发送邮件失败，返回 400
     * */
    @GetMapping(value = "/password",produces = "application/json; charset=utf-8")
    public void forgetPassword(@RequestParam(value = "account") String account, HttpServletResponse response){
        Boolean status=false;

        // 区分传入的是学生还是教师，调用不同的 Service
        if (account.length()>=studentAccountLength){
            status=studentService.forgetPassword(account);
        }
        else {
            status=teacherService.forgetPassword(account);
        }
        if (status){
            response.setStatus(200);
        }
        else {
            response.setStatus(400);
        }
    }

    /**
     * 修改邮箱
     *
     * @param request 请求头中存储着 jwt 信息
     * @param user 前端请求的 json 映射成的对象，只包修改后的 email
     * 若修改成功，返回 200
     * 若修改失败，返回 400
     * */
    @PutMapping(value = "/email",produces = "application/json; charset=utf-8")
    public void email(@RequestBody Teacher user,HttpServletRequest request, HttpServletResponse response)throws IOException {
        int modifyCount=0;
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        // 区分传入的是学生还是教师，调用不同的 Service
        if (jwtStudent!=null){
            Student tempStudent=new Student(jwtStudent.getAccount());
            tempStudent.setEmail(user.getEmail());
            modifyCount = studentService.updateStudent(tempStudent);
        }
        else {
            Teacher tempTeacher=new Teacher(jwtTeacher.getAccount());
            tempTeacher.setEmail(user.getEmail());
            modifyCount = teacherService.updateTeacher(tempTeacher);
        }
        if (modifyCount==0){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
        }
    }


}

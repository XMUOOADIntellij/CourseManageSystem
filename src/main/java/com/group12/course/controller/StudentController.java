package com.group12.course.controller;

import com.group12.course.entity.Student;
import com.group12.course.service.StudentService;
import com.group12.course.tools.Jwt;
import com.group12.course.controller.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 获取所有学生的记录
     *
     * @return 返回所有的学生的列表
     * */
    @GetMapping(value = "",produces = "application/json; charset=utf-8")
    public List<Student> getAllStudent(HttpServletResponse response){
        response.setStatus(200);
        return studentService.getAllStudent();
    }

    /**
     * 通过账号或者姓名获取学生信息
     * @param param 可以是学生姓名或者账号
     * @return 返回符合的学生的列表
     * 当是通过账号查询时，列表只会包含一个
     * */
    @GetMapping(value = "/searchStudent",produces = "application/json; charset=utf-8")
    public List<Student> searchStudent(@RequestParam(value = "identity") String param, HttpServletResponse response){
        List<Student> list = studentService.getStudentByParam(param.trim());
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
     * 通过学生id修改学生个人信息
     * @param studentId 学生id
     * @param student 修改后的学生对象
     * 当修改成功，返回200
     * 失败则只返回404
     * */
    @PutMapping(value = "/{studentId}/information",produces = "application/json; charset=utf-8")
    public StudentVO changeInformation(@PathVariable Long studentId, @RequestBody StudentVO student, HttpServletResponse response)throws IOException {
        student.setId(studentId);
        int count= studentService.changeStudentByID(new Student(student));
        if (count==0){
            response.setStatus(404);
            return null;
        }
        else {
            response.setStatus(200);
            return student;
        }
    }

    /**
     * 通过学生id重置密码
     * @param studentId 学生id
     * 当重置成功，返回200
     * 失败则只返回404
     * */
    @PutMapping(value = "/{studentId}/password",produces = "application/json; charset=utf-8")
    public void resetPassword(@PathVariable Long studentId,HttpServletResponse response)throws IOException {
        int count = studentService.resetPassword(studentId);
        if (count==0){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
            // 这里 API 标准组还返回了修改后的对象，没实现
            // 因为如果还要返回修改后的对象的话，
            // 得 UPDATE 之后再 SELECT
            // 但是这里返回的实际意义不大
        }
    }

    /**
     * 通过学生id删除学生
     * @param studentId 学生id
     * 当删除成功，返回200
     * 失败则只返回404
     * */
    @DeleteMapping(value = "/{studentId}",produces = "application/json; charset=utf-8")
    public void deleteStudent(@PathVariable Long studentId, HttpServletResponse response)throws IOException {
        int count = studentService.deleteStudent(studentId);
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
    public void active(@RequestBody Student user, HttpServletRequest request, HttpServletResponse response)throws IOException {
        int modifyCount=0;
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        // 区分传入的是学生还是教师，调用不同的 Service
        if (jwtStudent!=null){
            Student tempStudent=new Student(jwtStudent.getAccount());
            tempStudent.setPassword(user.getPassword());
            tempStudent.setEmail(user.getEmail());
            tempStudent.setActive(true);
            modifyCount = studentService.updateStudent(tempStudent);
        }
        if (modifyCount==0){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
        }
    }
}

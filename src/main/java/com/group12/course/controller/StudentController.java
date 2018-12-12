package com.group12.course.controller;

import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.service.StudentService;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
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
    @GetMapping(value = "/searchstudent",produces = "application/json; charset=utf-8")
    public List<Student> searchStudent(@RequestParam String param, HttpServletResponse response){
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

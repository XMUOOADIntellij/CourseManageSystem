package com.group12.course.controller;


import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.service.ScoreService;
import com.group12.course.tools.Jwt;
import com.group12.course.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;
    /**
     * 查看课程成绩
     * 在Service 老师和学生返回不同
     * @param request 请求
     * @return ScoreVo
     */
    @GetMapping("")
    public List<ScoreVO> getSeminarScore(HttpServletRequest request){
        return null;
    }

    @PutMapping("/{scoreId}")
    public Integer modifyScore(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);

        //return scoreService.modifyScore(jwtTeacher,seminarScore);
        return null;
    }
}

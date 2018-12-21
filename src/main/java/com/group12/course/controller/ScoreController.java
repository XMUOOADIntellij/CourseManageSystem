package com.group12.course.controller;


import com.group12.course.controller.vo.ScoreVO;
import com.group12.course.entity.RoundScore;
import com.group12.course.entity.SeminarScore;
import com.group12.course.entity.Teacher;
import com.group12.course.service.ScoreService;
import com.group12.course.tools.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public void getSeminarScore(HttpServletRequest request){
        return;
    }

    @PutMapping(value = "/{scoreId}")
    public Integer modifyScore(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);

        //return scoreService.modifyScore(jwtTeacher,seminarScore);
        return null;
    }

    @GetMapping(value = "/course/{courseId}")
    public List<ScoreVO> getCourseScore(@PathVariable Long courseId){
        List<ScoreVO> scoreVOList = new ArrayList<>();
        for(RoundScore item : scoreService.getStudentRoundScore(new Teacher(),courseId)){
            scoreVOList.add(new ScoreVO(item));
        }
        return  scoreVOList;
    }

}

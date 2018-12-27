package com.group12.course.controller;


import com.group12.course.controller.vo.RoundScoreVO;
import com.group12.course.controller.vo.SeminarScoreVO;
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


/**
 * 与分数相关controller
 * @author Y Jiang
 * @date 2018/12/22
 */
@RestController
@RequestMapping("")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @PutMapping(value = "/attendance/{attendanceId}/score")
    public Integer modifyScoreByAttendance(@PathVariable Long attendanceId,@RequestBody SeminarScoreVO seminarScoreVO,
                                           HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        return scoreService.modifyScoreByAttendance(teacher,new SeminarScore(seminarScoreVO),attendanceId);
    }

    @PutMapping(value="/seminar/{seminarId}/class/{classId}/score")
    public Integer modifyScoreBySeminar(@PathVariable Long seminarId,@PathVariable Long classId,
                                        @RequestBody SeminarScoreVO seminarScoreVO, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        SeminarScore seminarScore = new SeminarScore(seminarScoreVO);
        return scoreService.modiftScoreBySeminar(teacher,seminarScore,seminarId,classId);
    }

    /**
     * 查看课程下的轮成绩
     * @param courseId 课程id
     * @return 成绩列表
     */
    @GetMapping(value = "/course/{courseId}/round/{roundId}/score")
    public List<RoundScoreVO> getCourseScore(@PathVariable Long courseId,@PathVariable Long roundId,
                                             HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        List<RoundScoreVO> scoreVOList = new ArrayList<>();
        for (RoundScore item : scoreService.getCourseRoundScoreByTeacher(teacher, courseId,roundId)) {
            scoreVOList.add(new RoundScoreVO(item));
        }
        return scoreVOList;
    }


    @GetMapping(value = "/round/{roundId}/score")
    public List<SeminarScoreVO> getRoundSeminarScore(@PathVariable Long roundId,
                                                     @RequestParam Long teamId){

        List<SeminarScoreVO> seminarScoreVOList = new ArrayList<>();
        for(SeminarScore seminarScore:scoreService.getRoundSeminarScore(roundId,teamId)){
            seminarScoreVOList.add(new SeminarScoreVO(seminarScore));
        }
        return  seminarScoreVOList;
    }

    /**
     * 获得某次班级讨论课成绩
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @param request 请求
     * @return 讨论课成绩
     */
    @GetMapping(value = "/seminar/{seminarId}/class/{classId}/score")
    public List<SeminarScoreVO> getKlassSeminarScore(@PathVariable Long seminarId,@PathVariable Long classId,
                                                     HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        List<SeminarScoreVO> seminarScoreVOList = new ArrayList<>();
        for(SeminarScore seminarScore:scoreService.getKlassSeminarScore(teacher,seminarId,classId)){
            seminarScoreVOList.add(new SeminarScoreVO(seminarScore));
        }
        return  seminarScoreVOList;
    }

    /**
     * 学生查看某次展示的成绩
     * @param attendanceId 展示的id
     * @return 讨论课成绩
     */
    @GetMapping(value = "/attendance/{attendanceId}/score")
    public SeminarScoreVO getAttendanceScore(@PathVariable Long attendanceId){
        return new SeminarScoreVO(scoreService.getAttendanceScore(attendanceId));
    }


}

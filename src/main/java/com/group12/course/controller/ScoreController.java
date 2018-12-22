package com.group12.course.controller;


import com.group12.course.controller.vo.RoundScoreVO;
import com.group12.course.controller.vo.SeminarScoreVO;
import com.group12.course.entity.RoundScore;
import com.group12.course.entity.SeminarScore;
import com.group12.course.entity.Teacher;
import com.group12.course.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    /**
     * 查看课程成绩
     * 在Service 老师和学生返回不同
     *
     * @param request 请求
     * @return ScoreVo
     */
    @GetMapping("")
    public void getSeminarScore(HttpServletRequest request) {
        return;
    }

    @PutMapping(value = "/attendance/{attendanceId}/score")
    public Integer modifyScoreByAttendance(@PathVariable Long attendanceId,@RequestBody SeminarScoreVO seminarScoreVO) {

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        return scoreService.modifyScoreByAttendance(teacher,new SeminarScore(seminarScoreVO),attendanceId);
    }

    @PutMapping(value="/seminar/{seminarId}/score")
    public Integer modifyScoreBySeminar(@PathVariable Long seminarId,@RequestBody SeminarScoreVO seminarScoreVO){
        Teacher teacher =new Teacher();
        teacher.setId(1L);

        SeminarScore seminarScore = new SeminarScore(seminarScoreVO);
        return scoreService.modiftScoreBySeminar(teacher,seminarScore,seminarId);
    }

    /**
     * 查看课程下的轮成绩
     * @param courseId 课程id
     * @return 成绩列表
     */
    @GetMapping(value = "/course/{courseId}/score")
    public List<RoundScoreVO> getCourseScore(@PathVariable Long courseId) {

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        List<RoundScoreVO> scoreVOList = new ArrayList<>();
        for (RoundScore item : scoreService.getCourseRoundScoreByTeacher(teacher, courseId)) {
            scoreVOList.add(new RoundScoreVO(item));
        }
        return scoreVOList;
    }

    @GetMapping(value = "/round/{roundId}/score")
    public List<SeminarScoreVO> getRoundSeminarScore(@PathVariable Long roundId,
                                                     @RequestBody SeminarScoreVO seminarScoreVO){

        List<SeminarScoreVO> seminarScoreVOList = new ArrayList<>();
        for(SeminarScore seminarScore:scoreService.getRoundSeminarScore(roundId,seminarScoreVO.getTeamId())){
            seminarScoreVOList.add(new SeminarScoreVO(seminarScore));
        }
        return  seminarScoreVOList;
    }

    @GetMapping(value = "/attendance/{attendanceId}/score")
    public SeminarScoreVO getAttendanceScore(@PathVariable Long attendanceId){
        return new SeminarScoreVO(scoreService.getAttendanceScore(attendanceId));
    }

}

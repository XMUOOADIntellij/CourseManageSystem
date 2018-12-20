package com.group12.course.controller;

import com.group12.course.entity.*;
import com.group12.course.service.AttendanceService;
import com.group12.course.service.QuestionService;
import com.group12.course.service.SeminarService;
import com.group12.course.tools.Jwt;
import com.group12.course.controller.vo.AttendanceVo;
import com.group12.course.controller.vo.QuestionVO;
import com.group12.course.controller.vo.SeminarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 讨论课部分的 Controller
 * @author Y Jiang
 * @date  2018/12/12
 */
@RestController
@RequestMapping("/seminar")
public class SeminarController {
    @Autowired
    SeminarService seminarService;
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    QuestionService questionService;

    /**
     * 创建讨论课
     * @param seminarVo 讨论课信息
     * @return 讨论课id
     */
    @PostMapping(value= "" , produces = "application/json; charset=utf-8")
    public Long createSeminar(@RequestBody SeminarVO seminarVo, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);

        if(seminarVo.getMaxTeam()==null ||
           seminarVo.getSeminarName()==null||
           seminarVo.getCourseId()==null||
           seminarVo.getVisible()==null||
           seminarVo.getSeminarSerial()==null){
        }
        else{
            //TODO 信息不完全
        }
        Seminar seminar = new Seminar(seminarVo);
        return  seminarService.createSeminar(seminar,jwtTeacher);
    }

    /**
     * 删除讨论课
     * @param seminarId 根据id
     */
    @DeleteMapping(value="/{seminarId}", produces = "application/json; charset=utf-8")
    public Integer deleteSeminar(@PathVariable Long seminarId,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        return seminarService.deleteSeminar(seminarId,jwtTeacher);
    }

    /**
     * 修改课程下的讨论课
     * @param seminarVo 讨论课传输实体
     * @param seminarId 讨论课id
     */
    @PutMapping(value="/{seminarId}",produces = "application/json")
    public Integer modifySeminar(@RequestBody SeminarVO seminarVo,@PathVariable Long seminarId){
        //TODO 判断合法
        Seminar seminar =new Seminar(seminarVo);
        seminar.setId(seminarId);
        return seminarService.updateSeminar(seminar);
    }

    /**
     * 更改某班级讨论课（报告截止时间）
     * @param seminarId 讨论课id
     * @param classId   班级id
     * @param
     */
    @PutMapping(value="/{seminarId}/class/{classId}",produces = "application/json")
    public Integer modifyKlassSeminar(@PathVariable Long seminarId, @PathVariable Long classId,
                                      @RequestBody SeminarVO seminarVo){
        //TODO 判断合法
        KlassSeminar klassSeminar = new KlassSeminar(seminarVo);
        return seminarService.updateKlassSeminar(seminarId,classId,klassSeminar);
    }

    /**
     * 获得某班级的讨论课
     * @param seminarId 课程讨论课的id
     * @param classId 班级id
     * @return SeminarVO
     */
    @GetMapping(value="/{seminarId}/class/{classId}",produces = "application/json")
    public SeminarVO selectKlassSeminarBySeminarIdAndClassId(@PathVariable Long seminarId, @PathVariable Long classId){
       KlassSeminar record =  seminarService.selectKlassSeminar(seminarId,classId);
       return new SeminarVO(record);
    }


    /**
     ① 获得当前班级讨论课的展示报名
     ② 获得当前班级讨论课正在展示的小组      presented
     ③ 某一小组获得当前班级讨论课报名的展示   teamId
     * @param seminarId 课程讨论课
     * @param classId   班级id
     * @param presented 当前是否正在展示
     * @param teamId    找到某一小组报名的展示
     * @return Attendance
     */
    @GetMapping(value="/{seminarId}/class/{classId}/attendance")
    public List<AttendanceVo> getSeminarAttendance(@PathVariable Long seminarId, @PathVariable Long classId,
                                                   @RequestParam(value ="presented",required = false) Boolean presented,
                                                   @RequestParam(value="teamId",required = false) Long teamId) throws Exception{

        //TODO Present Socket解决？ Exception
        List<AttendanceVo> result = new ArrayList<>();
        if(teamId!=null){
            result.add(new AttendanceVo(attendanceService.getAttendance(classId,seminarId,teamId)));
        }
        else{
            for(Attendance item:attendanceService.getKlassSeminarAttendance(classId, seminarId)){
                result.add(new AttendanceVo(item));
            }
        }
        return result;
    }

    /**
     * 批量下载当前班级讨论课报告
     * @param seminarId 课程讨论课
     * @param classId 班级id
     */
    @GetMapping(value="/{seminarId}/class/{classId}/report")
    public void downloadAllReport(@PathVariable Long seminarId, @PathVariable Long classId,
                                  HttpServletResponse response){
        attendanceService.downloadAllReport(seminarId,classId,response);
    }

    /**
     * 批量下载当前班级讨论课展示ppt
     * @param seminarId 课程讨论课
     * @param classId 班级id
     */
    @GetMapping(value="/{seminarId}/class/{classId}/ppt")
    public void downloadAllPPT(@PathVariable Long seminarId, @PathVariable Long classId,
                               HttpServletResponse response) {
       attendanceService.downloadAllPpt(seminarId,classId,response);
    }

    /**
     * 报名某班级讨论课
     * @param seminarId  课程讨论课id
     * @param classId    班级id
     */
    @PostMapping(value="/{seminarId}/class/{classId}/attendance")
    public void enrollAttendance(@PathVariable Long seminarId, @PathVariable Long classId){
        //TODO RequstBody有teamid和order解决
        //TODO
        return;
    }

    @GetMapping(value="/{seminarId}/class/{classId}/question")
    public List<QuestionVO> getAllQuestion(@PathVariable Long seminarId, @PathVariable Long classId){
        List<Question> questionlist = questionService.getAllQuestion(seminarId,classId);
        List<QuestionVO> questionVOList = new ArrayList<>();
        for(Question item:questionlist){
            questionVOList.add(new QuestionVO(item));
        }
        return questionVOList;
    }

    @PostMapping(value="/{seminarId}/class/{classId}/question")
    public Long askQuestion(@PathVariable Long seminarId,@PathVariable Long classId,
                               @RequestBody QuestionVO questionVO,
                               HttpServletRequest request){

        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        return questionService.askQuestion(seminarId,classId,new Question(questionVO),jwtStudent);
    }
}

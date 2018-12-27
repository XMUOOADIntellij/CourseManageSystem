package com.group12.course.controller;

import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.entity.*;
import com.group12.course.exception.UnauthorizedOperationException;
import com.group12.course.service.AttendanceService;
import com.group12.course.service.QuestionService;
import com.group12.course.service.SeminarService;
import com.group12.course.tools.Jwt;
import com.group12.course.controller.vo.AttendanceVO;
import com.group12.course.controller.vo.QuestionVO;
import com.group12.course.controller.vo.SeminarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
     * 查找当前正在进行的讨论课
     * @param request 请求jwt
     * @return seminar
     */
    @GetMapping(value="")
    public SeminarVO getCurrentSeminar(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        Student student;
        KlassSeminar klassseminar;
        if(teacher==null){
            student = Jwt.unSign(token,Student.class);
            klassseminar = seminarService.getCurrentSeminar(student);
        }else {
            klassseminar = seminarService.getCurrentSeminar(teacher);
        }
        if(klassseminar==null){
            return null;
        }
        else {
            return new SeminarVO(klassseminar);
        }
    }

    /**
     * 创建讨论课
     * @param seminarVo 讨论课信息
     * @return 讨论课id
     */
    @PostMapping(value= "" , produces = "application/json; charset=utf-8")
    public ResponseEntity<SeminarVO> createSeminar(@Valid @RequestBody SeminarVO seminarVo, HttpServletRequest request){

        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        Seminar seminar;
        if(teacher!=null){
        seminar = new Seminar(seminarVo);
        seminarService.createSeminar(seminar,teacher);}
        else {
            throw new UnauthorizedOperationException("只有老师能创建");
        }

        return new ResponseEntity<>(new SeminarVO(seminar), HttpStatus.OK);
    }

    @GetMapping(value = "/{seminarId}")
    public ResponseEntity<SeminarVO> selectSeminarBySeminarId(@PathVariable Long seminarId){

        Seminar seminar = seminarService.selectSeminarById(seminarId);
        if(seminar ==null){
            throw  new RecordNotFoundException("Invalid seminar Id: "+seminarId);
        }
        else{
            return new ResponseEntity<>(new SeminarVO(seminar), HttpStatus.OK);
        }
    }

    /**
     * 删除讨论课
     * @param seminarId 根据id
     */
    @DeleteMapping(value="/{seminarId}", produces = "application/json; charset=utf-8")
    public Integer deleteSeminar(@PathVariable Long seminarId,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        return seminarService.deleteSeminar(seminarId,teacher);
    }

    /**
     * 修改课程下的讨论课
     * @param seminarVo 讨论课传输实体
     * @param seminarId 讨论课id
     */
    @PutMapping(value="/{seminarId}",produces = "application/json")
    public Integer modifySeminar(@RequestBody SeminarVO seminarVo,@PathVariable Long seminarId,
                                 HttpServletRequest request){
        Seminar seminar =new Seminar(seminarVo);
        seminar.setId(seminarId);
        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        return seminarService.updateSeminar(seminar,teacher);
    }

    /**
     * 更改某班级讨论课（报告截止时间）
     * @param seminarId 讨论课id
     * @param classId   班级id
     * @param
     */
    @PutMapping(value="/{seminarId}/class/{classId}",produces = "application/json")
    public Integer modifyKlassSeminar(@PathVariable Long seminarId, @PathVariable Long classId,
                                      @RequestBody SeminarVO seminarVo,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        KlassSeminar klassSeminar = new KlassSeminar(seminarVo);
        klassSeminar.setKlass(new Klass());
        klassSeminar.getKlass().setId(classId);
        klassSeminar.setSeminar(new Seminar());
        klassSeminar.getSeminar().setId(seminarId);

        return seminarService.updateKlassSeminar(klassSeminar,teacher);
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


    //----------------------------------------------------------------------------------//



    /**
     ① 获得当前班级讨论课的展示报名
     ② 获得当前班级讨论课正在展示的小组presented
     * @param seminarId 课程讨论课
     * @param classId   班级id
     * @param presented 当前是否正在展示
     * @return Attendance
     */
    @GetMapping(value="/{seminarId}/class/{classId}/attendance")
    public List<AttendanceVO> getSeminarAttendance(@PathVariable Long seminarId, @PathVariable Long classId,
                                                   @RequestParam(value ="presented",required = false) Boolean presented) throws Exception{

        List<AttendanceVO> result = new ArrayList<>();
        if(presented!=null){
            result.add(new AttendanceVO(attendanceService.getCurrentAttendanceBySeminarIdAndClassId(classId,seminarId)));
        }
        else{
            for(Attendance item:attendanceService.getKlassSeminarAttendance(classId, seminarId)){
                result.add(new AttendanceVO(item));
            }
        }
        return result;
    }

    /**
     * 某小组获得自己队伍的展示
     * @param seminarId 讨论课Id
     * @return 展示信息
     */
    @GetMapping(value = "/{seminarId}/class/{classId}/teamAttendance")
    public AttendanceVO getTeamAttendance(@PathVariable Long seminarId,@PathVariable Long classId,
                                          HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Student student = Jwt.unSign(token,Student.class);
        return new AttendanceVO(
                attendanceService.getTeamAttendance(seminarId,classId,student)
        );
    }

    /**
     * 报名某班级讨论课
     * @param seminarId  课程讨论课id
     */
    @PostMapping(value="/{seminarId}/class/{classId}/attendance")
    public Long enrollAttendance(@PathVariable Long seminarId,@RequestBody AttendanceVO attendanceVo,
                                 @PathVariable Long classId, HttpServletRequest request){

        String token = request.getHeader("Authorization");
        Student student = Jwt.unSign(token,Student.class);
        Attendance attendance = new Attendance(attendanceVo);
        return attendanceService.enrollAttendance(seminarId,classId,attendance,student);
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




    @GetMapping(value="/{seminarId}/class/{classId}/attendance/{attendanceId}/question")
    public List<QuestionVO> getAttendanceQuestion(@PathVariable Long seminarId, @PathVariable Long classId,
                                                  @PathVariable Long attendanceId,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);

        List<Question> questionlist = questionService.getAttendanceQuestion(teacher,seminarId,classId,attendanceId);
        List<QuestionVO> questionVOList = new ArrayList<>();
        for(Question item:questionlist){
            questionVOList.add(new QuestionVO(item));
        }
        return questionVOList;
    }


}

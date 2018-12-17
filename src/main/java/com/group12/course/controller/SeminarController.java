package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.service.AttendanceService;
import com.group12.course.service.SeminarService;
import com.group12.course.vo.AttendanceVo;
import com.group12.course.vo.SeminarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 创建讨论课
     * @param seminarVo 讨论课信息
     * @return 讨论课id
     */
    @PostMapping(value= "" , produces = "application/json; charset=utf-8")
    public Long createSeminar(@RequestBody SeminarVo seminarVo){
        //TODO if(seminar name/maxteam/isvisible/serial/courseId 信息不完整
        Seminar seminar = new Seminar(seminarVo);
        return  seminarService.createSeminar(seminar);
    }

    /**
     * 删除讨论课
     * @param seminarId 根据id
     */
    @DeleteMapping(value="/{seminarId}", produces = "application/json; charset=utf-8")
    public Integer deleteSeminar(@PathVariable Long seminarId){
        return seminarService.deleteSeminar(seminarId);
    }

    /**
     * 获得某班级的讨论课
     * @param seminarId 课程讨论课的id
     * @param classId 班级id
     * @return SeminarVo
     */
    @GetMapping(value="/{seminarId}/class/{classId}",produces = "application/json")
    public SeminarVo selectKlassSeminarBySeminarIdAndClassId(@PathVariable Long seminarId, @PathVariable Long classId){
       KlassSeminar record =  seminarService.selectKlassSeminarBySeminarIdAndClassId(seminarId,classId);
       return new SeminarVo(record);
    }

    /**
     * 修改课程下的讨论课
     * @param seminarVo 讨论课传输实体
     * @param seminarId 讨论课id
     */
    @PutMapping(value="/{seminarId}",produces = "application/json")
    public Integer modifySeminar(@RequestBody SeminarVo seminarVo,@PathVariable Long seminarId){
        //TODO 判断合法
        Seminar seminar =new Seminar(seminarVo);
        return seminarService.updateSeminar(seminar,seminarId);
    }

    /**
     * 更改某班级讨论课（报告截止时间）
     * @param seminarId 讨论课id
     * @param classId   班级id
     * @param
     */
    @PutMapping(value="/{seminarId}/class/{classId}",produces = "application/json")
    public Integer modifyKlassSeminar(@PathVariable Long seminarId, @PathVariable Long classId,@RequestBody SeminarVo seminarVo){
        //TODO 判断合法
        KlassSeminar klassSeminar = new KlassSeminar(seminarVo);
        return seminarService.updateKlassSeminar(seminarId,classId,klassSeminar);
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
    public void downloadAllReport(@PathVariable Long seminarId, @PathVariable Long classId, HttpServletResponse response){
        //TODO
        return;
    }

    /**
     * 批量下载当前班级讨论课展示ppt
     * @param seminarId 课程讨论课
     * @param classId 班级id
     */
    @GetMapping(value="/{seminarId}/class/{classId}/ppt")
    public void downloadAllPPT(@PathVariable Long seminarId, @PathVariable Long classId,
                               HttpServletResponse response) {
       // attendanceService.downloadAllPpt(seminarId,classId,response);
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

}

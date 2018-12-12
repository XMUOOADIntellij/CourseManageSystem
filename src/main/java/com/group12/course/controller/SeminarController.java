package com.group12.course.controller;

import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * 创建讨论课
     * @param seminar 讨论课信息
     * @return 讨论课id
     */
    @PostMapping(value= "" , produces = "application/json; charset=utf-8")
    public int createSeminar(@RequestBody Seminar seminar){
        //TODO
       return 0;
    }

    /**
     * 删除讨论课
     * @param seminarId 根据id
     */
    @DeleteMapping(value="/{seminarId}", produces = "application/json; charset=utf-8")
    public void deleteSeminar(@PathVariable Long seminarId){
        //TODO
        return;
    }

    /**
     * 获得某班级的讨论课
     * @param seminarId 课程讨论课的id
     * @param classId 班级id
     * @return
     */
    @GetMapping(value="/{seminarId}/class/{classId}",produces = "application/json")
    public KlassSeminar getKlassSeminar(@PathVariable Long seminarId, @PathVariable Long classId){
        //TODO
        return null;
    }

    /**
     * 修改课程下的讨论课
     * @param seminar
     */
    @PutMapping(value="/{seminarId}",produces = "application/json")
    public void modifySeminar(@RequestBody Seminar seminar){
        //TODO
        return;
    }

    /**
     * 更改某班级讨论课（报告截止时间）
     * @param seminarId
     * @param classId
     */
    @PutMapping(value="/{seminarId}/class/{classId}",produces = "application/json")
    public void modifyKlassSeminar(@PathVariable Long seminarId, @PathVariable Long classId,@RequestBody KlassSeminar klassSeminar){
        //TODO
        return;
    }


    /**
     * ① 获得当前班级讨论课的展示报名
     * ② 获得当前班级讨论课正在展示的小组
     * @param seminarId 课程讨论课
     * @param classId   班级id
     * @param presented 当前是否正在展示
     * @return Attendance
     */
    @GetMapping(value="/{seminarId}/class/{classId}/attendance")
    public Attendance  getSeminarAttendance(@PathVariable Long seminarId, @PathVariable Long classId,@RequestParam(value = "presented") Boolean presented){
        //TODO
        return null;
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
    public void downloadAllPPT(@PathVariable Long seminarId, @PathVariable Long classId, HttpServletResponse response){
        //TODO
        return;
    }

    /**
     * 报名某班级讨论课
     * @param seminarId  课程讨论课id
     * @param classId 班级id
     */
    @PostMapping(value="/{seminarId}/class/{classId}/attendance")
    public void enrollAttendance(@PathVariable Long seminarId, @PathVariable Long classId){
        //TODO RequstBody有teamid和order解决
        //TODO
        return;
    }








}

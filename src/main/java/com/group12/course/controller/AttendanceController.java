package com.group12.course.controller;

import com.group12.course.entity.Attendance;
import com.group12.course.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;
    //TODO 规范返回

    /**
     * 更改报名的次序
     * @param record Attendance
     * @return
     */
    @PutMapping(value="/{attendanceId}")
    public Integer changeOrder(@PathVariable Long attendanceId,@RequestBody Attendance record){
        return null;
    }

    /**
     * 取消报名班级讨论课
     * @param attendanceId
     */
    @DeleteMapping(value="/{attendanceId}")
    public Integer cancelAttendance(@PathVariable Long attendanceId){
        return null;
    }

    /**
     * 下载某个展示报告
     * @param attendanceId 某报名的展示
     * @param response     回应
     */
    @GetMapping(value="/{attendanceId}/report")
    public void downloadReport(@PathVariable Long attendanceId, HttpServletResponse response){
        //TODO 下载某个展示报告
        return;
    }

    /**
     * 上传某个展示报告
     * @param attendanceId 某报名的展示
     * @param request 请求
     */
    @PostMapping(value="/{attendanceId}/report")
    public void uploadReport(@PathVariable Long attendanceId,HttpServletRequest request){
        //TODO 上传某个展示报告
        return;
    }

    /**
     * 重传某个展示报告
     * @param attendanceId 某报名的展示
     * @param request 请求
     */
    @PutMapping(value="/{attendanceId}/report")
    public void modifyReport(@PathVariable Long attendanceId,HttpServletRequest request){
        //TODO 重传某个展示报告
        return;
    }

    /**
     * 下载某个展示ppt
     * @param attendanceId 某报名的展示
     * @param response     回应
     */
    @GetMapping(value="/{attendanceId}/ppt")
    public void downloadPPT(@PathVariable Long attendanceId, HttpServletResponse response){
        //TODO 下载某个展示ppt
        return;
    }

    /**
     * 上传某个展示ppt
     * @param attendanceId 某报名的展示
     * @param request 请求
     */
    @PostMapping(value="/{attendanceId}/ppt")
    public void uploadPPT(@PathVariable Long attendanceId,HttpServletRequest request){
        //TODO 上传某个展示ppt
        return;
    }

    /**
     * 重传某个展示ppt
     * @param attendanceId 某报名的展示
     * @param request 请求
     */
    @PutMapping(value="/{attendanceId}/ppt")
    public void modifyPPT(@PathVariable Long attendanceId,HttpServletRequest request){
        //TODO 重传某个展示ppt
        return;
    }


}

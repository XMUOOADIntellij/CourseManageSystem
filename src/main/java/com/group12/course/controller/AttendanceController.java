package com.group12.course.controller;

import com.group12.course.controller.vo.AttendanceVo;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.Student;
import com.group12.course.service.AttendanceService;
import com.group12.course.tools.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Integer changeOrder(@PathVariable Long attendanceId,@RequestBody AttendanceVo record){
        Attendance attendance = new Attendance(record);
        Student student = new Student();
        student.setId(999L);
        attendance.setId(attendanceId);

        return attendanceService.changeAttendanceOrder(attendance,student);
    }

    /**
     * 取消报名班级讨论课
     * @param attendanceId
     */
    @DeleteMapping(value="/{attendanceId}")
    public Integer cancelAttendance(@PathVariable Long attendanceId){

        Student student = new Student();
        student.setId(999L);

        return attendanceService.cancelAttendance(attendanceId,student);
    }


    /**
     * 下载某个展示报告
     * @param attendanceId 某报名的展示
     * @param response     回应
     */
    @GetMapping(value="/{attendanceId}/report")
    public void downloadReport(@PathVariable Long attendanceId, HttpServletResponse response){
        attendanceService.downloadReport(attendanceId,response);
    }

    /**
     * 上传某个展示报告
     * @param file  报告文件
     * @param attendanceId 展示的id
     */
    @PostMapping(value="/{attendanceId}/report",produces = "multipart/form-data; charset=utf-8")
    public String uploadReport(@RequestParam("file") MultipartFile file, @PathVariable Long attendanceId,
                               HttpServletRequest request) {

        Student student = new Student();
        student.setId(999L);
        return attendanceService.uploadReport(attendanceId,file,student);
    }

    /**
     * 重传某个展示报告
     * @param file 文件名
     * @param attendanceId 展示的id
     * @return 文件的url
     */
    @PutMapping(value="/{attendanceId}/report")
    public String modifyReport(@RequestParam("file") MultipartFile file,@PathVariable Long attendanceId,
                               HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        return attendanceService.uploadReport(attendanceId,file,jwtStudent);
    }

    /**
     * 下载某个展示ppt
     * @param attendanceId 某报名的展示
     * @param response     回应
     */
    @GetMapping(value="/{attendanceId}/ppt")
    public void downloadPPT(@PathVariable Long attendanceId, HttpServletResponse response){
        attendanceService.downloadPpt(attendanceId,response);
    }

    /**
     * 上传某个展示ppt
     * @param file 文件
     * @param attendanceId 展示的id
     * @return 文件的url
     */
    @PostMapping(value="/{attendanceId}/ppt",produces = "application/json; charset=utf-8")
    public String uploadPPT(@RequestParam("file") MultipartFile file,@PathVariable Long attendanceId,
                            HttpServletRequest request){
        Student student = new Student();
        student.setId(999L);
        return attendanceService.uploadPpt(attendanceId,file,student);
    }

    /**
     * 重传ppt
     * @param file 文件
     * @param attendanceId 展示id
     * @return 文件的url
     */
    @PutMapping(value="/{attendanceId}/ppt")
    public String modifyPPT(@RequestParam("file") MultipartFile file,@PathVariable Long attendanceId,
                            HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        return attendanceService.uploadPpt(attendanceId,file,jwtStudent);
    }

}

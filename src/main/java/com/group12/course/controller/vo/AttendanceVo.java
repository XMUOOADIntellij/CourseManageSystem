package com.group12.course.controller.vo;

import com.group12.course.entity.Attendance;

public class AttendanceVO {

    /**
     * Attendance 的属性
     */
    private Long id;

    private Integer teamOrder;

    private Boolean presented;

    private String reportName;

    private String reportUrl;

    private String pptName;

    private String pptUrl;

    /**
     * 添加的属性
     */

    private Long classId;
    private Integer classSerial;

    private Long teamId;
    private Integer teamSerial;

    private Long seminarId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
    }

    public Boolean getPresented() {
        return presented;
    }

    public void setPresented(Boolean presented) {
        this.presented = presented;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getPptName() {
        return pptName;
    }

    public void setPptName(String pptName) {
        this.pptName = pptName;
    }

    public String getPptUrl() {
        return pptUrl;
    }

    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getClassSerial() {
        return classSerial;
    }

    public void setClassSerial(Integer classSerial) {
        this.classSerial = classSerial;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }


    public AttendanceVO(){}
    public AttendanceVO(Attendance attendance) {
        this.id = attendance.getId();
        this.pptName = attendance.getPptName();
        this.pptUrl = attendance.getPptUrl();
        this.reportName =attendance.getReportName();
        this.reportUrl=attendance.getReportUrl();
        this.presented=attendance.getPresented();
        this.teamOrder=attendance.getTeamOrder();

        this.classSerial=attendance.getKlassSeminar().getKlass().getKlassSerial();
        this.teamSerial = attendance.getTeam().getTeamSerial();
    }
}

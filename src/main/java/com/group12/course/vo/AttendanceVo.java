package com.group12.course.vo;

import com.group12.course.entity.Attendance;

public class AttendanceVo {

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
    private Integer classGrade;
    private Integer classSerial;

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

    public Integer getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(Integer classGrade) {
        this.classGrade = classGrade;
    }

    public Integer getClassSerial() {
        return classSerial;
    }

    public void setClassSerial(Integer classSerial) {
        this.classSerial = classSerial;
    }

    public AttendanceVo(){}
    public AttendanceVo(Attendance attendance) {

        this.id = attendance.getId();
        this.pptName = attendance.getPptName();
        this.pptUrl = attendance.getPptUrl();
        this.reportName =attendance.getReportName();
        this.reportUrl=attendance.getReportUrl();
        this.presented=attendance.getPresented();
        this.teamOrder=attendance.getTeamOrder();
        this.classGrade=attendance.getKlassSeminar().getKlass().getGrade().intValue();
        this.classSerial=attendance.getKlassSeminar().getKlass().getKlassSerial();
    }
}

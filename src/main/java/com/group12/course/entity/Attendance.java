package com.group12.course.entity;


import com.group12.course.vo.AttendanceVo;
import org.apache.ibatis.type.Alias;

/**
 * 班级讨论课展示信息
 * @author Y Jiang
 * @date 2018/12/12
 */

@Alias("attendance")
public class Attendance {
    private Long id;

    /**
     * 一次报名展示对应一节讨论课
     */
    private KlassSeminar klassSeminar;

    /**
     * 一次报名展示对应一个小组
     */
    private Team team;

    private Integer teamOrder;

    private Boolean presented;

    private String reportName;

    private String reportUrl;

    private String pptName;

    private String pptUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KlassSeminar getKlassSeminar() {
        return klassSeminar;
    }

    public void setKlassSeminar(KlassSeminar klassSeminar) {
        this.klassSeminar = klassSeminar;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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


    public Attendance(){}
    public Attendance(AttendanceVo attendanceVo){
        this.id =attendanceVo.getId();
        this.pptName = attendanceVo.getPptName();
        this.reportName =attendanceVo.getReportName();
        this.pptUrl = attendanceVo.getPptUrl();
        this.reportUrl =attendanceVo.getReportUrl();
        this.presented =attendanceVo.getPresented();
        this.teamOrder =attendanceVo.getTeamOrder();

    }
}
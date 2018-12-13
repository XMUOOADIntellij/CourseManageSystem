package com.group12.course.entity;


/**
 * 班级讨论课展示信息
 * @author Y Jiang
 * @date 2018/12/12
 */

public class Attendance {
    private Long id;

    private KlassSeminar klassSeminar;

    private Team team;

    private Integer teamOrder;

    private Boolean presented;

    private String reportName;

    private String reprotUrl;

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

    public String getReprotUrl() {
        return reprotUrl;
    }

    public void setReprotUrl(String reprotUrl) {
        this.reprotUrl = reprotUrl;
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
}
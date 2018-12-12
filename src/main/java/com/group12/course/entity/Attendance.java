package com.group12.course.entity;

public class Attendance {
    private Long id;

    private Long klassSeminarId;

    private Long teamId;

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

    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Long klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
    }

    public Boolean getIsPresent() {
        return presented;
    }

    public void setIsPresent(Boolean presented) {
        this.presented = presented;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    public String getReprotUrl() {
        return reprotUrl;
    }

    public void setReprotUrl(String reprotUrl) {
        this.reprotUrl = reprotUrl == null ? null : reprotUrl.trim();
    }

    public String getPptName() {
        return pptName;
    }

    public void setPptName(String pptName) {
        this.pptName = pptName == null ? null : pptName.trim();
    }

    public String getPptUrl() {
        return pptUrl;
    }

    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl == null ? null : pptUrl.trim();
    }
}
package com.group12.course.entity;

import java.util.Date;

public class Presentation {
    private Long id;

    private Long teamId;

    private String presentationMaterialName;

    private String reportMaterialName;

    private String presentationMaterialUrl;

    private String reportMaterialUrl;

    private Boolean status;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getPresentationMaterialName() {
        return presentationMaterialName;
    }

    public void setPresentationMaterialName(String presentationMaterialName) {
        this.presentationMaterialName = presentationMaterialName == null ? null : presentationMaterialName.trim();
    }

    public String getReportMaterialName() {
        return reportMaterialName;
    }

    public void setReportMaterialName(String reportMaterialName) {
        this.reportMaterialName = reportMaterialName == null ? null : reportMaterialName.trim();
    }

    public String getPresentationMaterialUrl() {
        return presentationMaterialUrl;
    }

    public void setPresentationMaterialUrl(String presentationMaterialUrl) {
        this.presentationMaterialUrl = presentationMaterialUrl == null ? null : presentationMaterialUrl.trim();
    }

    public String getReportMaterialUrl() {
        return reportMaterialUrl;
    }

    public void setReportMaterialUrl(String reportMaterialUrl) {
        this.reportMaterialUrl = reportMaterialUrl == null ? null : reportMaterialUrl.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
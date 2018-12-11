package com.group12.course.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Presentation {
    private Long id;

    private Long teamId;

    private String presentationMaterialName;

    private String reportMaterialName;

    private String presentationMaterialUrl;

    private String reportMaterialUrl;

    private Integer presentationOrder;

    private Boolean presented;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

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
        this.presentationMaterialName = presentationMaterialName;
    }

    public String getReportMaterialName() {
        return reportMaterialName;
    }

    public void setReportMaterialName(String reportMaterialName) {
        this.reportMaterialName = reportMaterialName;
    }

    public String getPresentationMaterialUrl() {
        return presentationMaterialUrl;
    }

    public void setPresentationMaterialUrl(String presentationMaterialUrl) {
        this.presentationMaterialUrl = presentationMaterialUrl;
    }

    public String getReportMaterialUrl() {
        return reportMaterialUrl;
    }

    public void setReportMaterialUrl(String reportMaterialUrl) {
        this.reportMaterialUrl = reportMaterialUrl;
    }

    public Integer getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(Integer presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public Boolean getPresented() {
        return presented;
    }

    public void setPresented(Boolean presented) {
        this.presented = presented;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
}
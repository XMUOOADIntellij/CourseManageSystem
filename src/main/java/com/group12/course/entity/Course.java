package com.group12.course.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Course 实体对象
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class Course {
    private Long id;

    private Long teacherId;

    private String courseName;

    private String introduction;

    private Boolean presentationPercentage;

    private Boolean questionPercentage;

    private Boolean reportPercentage;

    private Date teamStartTime;

    private Date teamEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Boolean getPresentationPercentage() {
        return presentationPercentage;
    }

    public void setPresentationPercentage(Boolean presentationPercentage) {
        this.presentationPercentage = presentationPercentage;
    }

    public Boolean getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(Boolean questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public Boolean getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(Boolean reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public Date getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(Date teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public Date getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(Date teamEndTime) {
        this.teamEndTime = teamEndTime;
    }
}
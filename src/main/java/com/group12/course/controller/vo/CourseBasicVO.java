package com.group12.course.controller.vo;

import com.group12.course.entity.Course;

import java.time.LocalDateTime;

public class CourseBasicVO {

    private Long id;

    private Long teacherId;

    private String courseName;

    private String introduction;

    private Integer presentationPercentage;

    private Integer questionPercentage;

    private Integer reportPercentage;

    private LocalDateTime teamStartTime;

    private LocalDateTime teamEndTime;

    public CourseBasicVO(Course course){
        this.id = course.getId();
        this.teacherId = course.getTeacher().getId();
        this.courseName = course.getCourseName();
        this.introduction = course.getIntroduction();
        this.presentationPercentage = course.getPresentationPercentage();
        this.questionPercentage = course.getQuestionPercentage();
        this.reportPercentage = course.getReportPercentage();
        this.teamStartTime = course.getTeamStartTime();
        this.teamEndTime = course.getTeamEndTime();
    }

    @Override
    public String toString() {
        return "CourseBasicVO{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", courseName='" + courseName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", presentationPercentage=" + presentationPercentage +
                ", questionPercentage=" + questionPercentage +
                ", reportPercentage=" + reportPercentage +
                ", teamStartTime=" + teamStartTime +
                ", teamEndTime=" + teamEndTime +
                '}';
    }

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
        this.courseName = courseName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPresentationPercentage() {
        return presentationPercentage;
    }

    public void setPresentationPercentage(Integer presentationPercentage) {
        this.presentationPercentage = presentationPercentage;
    }

    public Integer getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(Integer questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public Integer getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(Integer reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public LocalDateTime getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(LocalDateTime teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public LocalDateTime getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(LocalDateTime teamEndTime) {
        this.teamEndTime = teamEndTime;
    }
}

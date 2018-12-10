package com.group12.course.entity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Course ENTITY
 * @author Y Jiang
 * @date 2018/12/1
 *
 */
public class Course {
    private Long id;

    private Long teacherNum;

    private String courseName;

    private String introduction;

    private Integer prePercentage;

    private Integer reportPercentage;

    private Integer quesPercentage;

    private LocalDateTime teamStartDate;

    private LocalDateTime teamEndDate;

    public Course(){

    }
    public Course(Long id, Long teacherNum, String courseName, String introduction, Integer prePercentage, Integer reportPercentage, Integer quesPercentage, LocalDateTime teamStartDate, LocalDateTime teamEndDate) {
        this.id = id;
        this.teacherNum = teacherNum;
        this.courseName = courseName;
        this.introduction = introduction;
        this.prePercentage = prePercentage;
        this.reportPercentage = reportPercentage;
        this.quesPercentage = quesPercentage;
        this.teamStartDate = teamStartDate;
        this.teamEndDate = teamEndDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Long teacherNum) {
        this.teacherNum = teacherNum;
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

    public Integer getPrePercentage() {
        return prePercentage;
    }

    public void setPrePercentage(Integer prePercentage) {
        this.prePercentage = prePercentage;
    }

    public Integer getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(Integer reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public Integer getQuesPercentage() {
        return quesPercentage;
    }

    public void setQuesPercentage(Integer quesPercentage) {
        this.quesPercentage = quesPercentage;
    }

    public LocalDateTime getTeamStartDate() {
        return teamStartDate;
    }

    public void setTeamStartDate(LocalDateTime teamStartDate) {
        this.teamStartDate = teamStartDate;
    }

    public LocalDateTime getTeamEndDate() {
        return teamEndDate;
    }

    public void setTeamEndDate(LocalDateTime teamEndDate) {
        this.teamEndDate = teamEndDate;
    }

    @Override
    public String toString(){
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", prePercentage='" + prePercentage + '\'' +
                ", reportPercentage=" + reportPercentage + '\'' +
                ", quesPercentage=" + quesPercentage + '\'' +
                ", teamStartDate=" + teamStartDate + '\'' +
                ", teamEndDate=" + teamEndDate + '\'' +
                ", teamStartDate=" + reportPercentage +
                '}';
    }
}
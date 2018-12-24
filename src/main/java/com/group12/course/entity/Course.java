package com.group12.course.entity;

import com.group12.course.controller.vo.CourseVO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Course 实体对象
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class Course implements Serializable {
    private Long id;

    private Teacher teacher;

    private String courseName;

    private String introduction;

    private Integer presentationPercentage;

    private Integer questionPercentage;

    private Integer reportPercentage;

    private LocalDateTime teamStartTime;

    private LocalDateTime teamEndTime;

    private Long teamMainCourseId;

    private Long seminarMainCourseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getcourseName() {
        return courseName;
    }

    public void setcourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
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

    public Long getTeamMainCourseId() {
        return teamMainCourseId;
    }

    public void setTeamMainCourseId(Long teamMainCourseId) {
        this.teamMainCourseId = teamMainCourseId;
    }

    public Long getSeminarMainCourseId() {
        return seminarMainCourseId;
    }

    public void setSeminarMainCourseId(Long seminarMainCourseId) {
        this.seminarMainCourseId = seminarMainCourseId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", courseName='" + courseName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", presentationPercentage=" + presentationPercentage +
                ", questionPercentage=" + questionPercentage +
                ", reportPercentage=" + reportPercentage +
                ", teamStartTime=" + teamStartTime +
                ", teamEndTime=" + teamEndTime +
                '}';
    }

    public Course(){

    }
    public Course(Long id) {
        this.id = id;
    }


    public Course(CourseVO courseVO) {

        this.courseName = courseVO.getcourseName();
        this.introduction = courseVO.getIntroduction();
        this.presentationPercentage = courseVO.getPresentationPercentage();
        this.questionPercentage = courseVO.getQuestionPercentage();
        this.reportPercentage = courseVO.getReportPercentage();
        this.teamStartTime = courseVO.getTeamStartTime();
        this.teamEndTime = courseVO.getTeamEndTime();
    }
}
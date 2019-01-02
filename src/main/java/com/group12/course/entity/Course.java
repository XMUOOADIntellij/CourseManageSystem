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
public class Course  {
    private Long id;

    private Teacher teacher;

    private String courseName;

    private String introduction;

    private Integer presentationPercentage;

    private Integer questionPercentage;

    private Integer reportPercentage;

    private LocalDateTime teamStartTime;

    private LocalDateTime teamEndTime;

    private Course teamMainCourse;

    private Course seminarMainCourse;

    private Course course;

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

    public Course getTeamMainCourse() {
        return teamMainCourse;
    }

    public void setTeamMainCourse(Course teamMainCourse) {
        this.teamMainCourse = teamMainCourse;
    }

    public Course getSeminarMainCourse() {
        return seminarMainCourse;
    }

    public void setSeminarMainCourse(Course seminarMainCourse) {
        this.seminarMainCourse = seminarMainCourse;
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
                ", teamMainCourse=" + teamMainCourse +
                ", seminarMainCourse=" + seminarMainCourse +
                '}';
    }

    public Course(){

    }
    public Course(Long id) {
        this.id = id;
    }


    public Course(CourseVO courseVO) {

        this.courseName = courseVO.getCourseName();
        this.introduction = courseVO.getIntroduction();
        this.presentationPercentage = courseVO.getPresentationPercentage();
        this.questionPercentage = courseVO.getQuestionPercentage();
        this.reportPercentage = courseVO.getReportPercentage();
        this.teamStartTime = courseVO.getTeamStartTime();
        this.teamEndTime = courseVO.getTeamEndTime();
    }
}
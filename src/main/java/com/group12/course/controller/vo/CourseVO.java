package com.group12.course.controller.vo;

import com.group12.course.entity.Course;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.entity.strategy.CourseMemberLimitStrategy;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
public class CourseVO {

    private String courseName;

    private String introduction;

    private Integer presentationPercentage;

    private Integer questionPercentage;

    private Integer reportPercentage;

    private LocalDateTime teamStartTime;

    private LocalDateTime teamEndTime;

    private Integer maxMember;

    private Integer minMember;

    List<Course> conflictCourseList;

    public String getcourseName() {
        return courseName;
    }

    public void setcourseName(String courseName) {
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

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public List<Course> getConflictCourseList() {
        return conflictCourseList;
    }

    public void setConflictCourseList(List<Course> conflictCourseList) {
        this.conflictCourseList = conflictCourseList;
    }

    public CourseVO(){}

    public CourseVO(Course course){

        this.courseName = course.getCourseName();
        this.introduction = course.getIntroduction();
        this.presentationPercentage = course.getPresentationPercentage();
        this.questionPercentage = course.getQuestionPercentage();
        this.reportPercentage = course.getReportPercentage();
        this.teamStartTime = course.getTeamStartTime();
        this.teamEndTime = course.getTeamEndTime();
    }

//    public CourseVO(CourseMemberLimitStrategy courseMemberLimitStrategy){
//        //组队规则属性
//        this.minMember = courseMemberLimitStrategy.getMinMember();
//        this.maxMember = courseMemberLimitStrategy.getMaxMember();
//
//        //课程属性
//        Course course = courseMemberLimitStrategy.getCourse();
//        this.id = course.getId();
//        this.teacherId = course.getTeacher().getId();
//        this.courseName = course.getcourseName();
//        this.introduction = course.getIntroduction();
//        this.presentationPercentage = course.getPresentationPercentage();
//        this.questionPercentage = course.getQuestionPercentage();
//        this.reportPercentage = course.getReportPercentage();
//        this.teamStartTime = course.getTeamStartTime();
//        this.teamEndTime = course.getTeamEndTime();
//    }

}

package com.group12.course.controller.vo;

import com.group12.course.entity.Course;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.entity.strategy.CourseMemberLimitStrategy;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * relation为1表示均满足，为0表示满足其一
 */
public class CourseVO {

    private String courseName;

    private String introduction;

    private Integer presentationPercentage;

    private Integer questionPercentage;

    private Integer reportPercentage;

    private LocalDateTime teamStartTime;

    private LocalDateTime teamEndTime;

    private Integer teamMaxMember;

    private Integer teamMinMember;

    private List<CourseMemberLimitVO> courseMemberLimitVOList;

    private Integer relation;

    private List<List<Long>> conflictCourseLists;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getTeamMaxMember() {
        return teamMaxMember;
    }

    public void setTeamMaxMember(Integer teamMaxMember) {
        this.teamMaxMember = teamMaxMember;
    }

    public Integer getTeamMingMember() {
        return teamMinMember;
    }

    public void setTeamMingMember(Integer teamMingMember) {
        this.teamMinMember = teamMingMember;
    }

    public List<CourseMemberLimitVO> getCourseMemberLimitVOList() {
        return courseMemberLimitVOList;
    }

    public void setCourseMemberLimitVOList(List<CourseMemberLimitVO> courseMemberLimitVOList) {
        this.courseMemberLimitVOList = courseMemberLimitVOList;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public List<List<Long>> getConflictCourseLists() {
        return conflictCourseLists;
    }

    public void setConflictCourseLists(List<List<Long>> conflictCourseLists) {
        this.conflictCourseLists = conflictCourseLists;
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

    @Override
    public String toString() {
        return "CourseVO{" +
                "courseName='" + courseName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", presentationPercentage=" + presentationPercentage +
                ", questionPercentage=" + questionPercentage +
                ", reportPercentage=" + reportPercentage +
                ", teamStartTime=" + teamStartTime +
                ", teamEndTime=" + teamEndTime +
                ", teamMaxMember=" + teamMaxMember +
                ", teamMingMember=" + teamMinMember +
                ", courseMemberLimitVOList=" + courseMemberLimitVOList +
                ", CourseRequire=" + relation +
                ", conflictCoursesList=" + conflictCourseLists +
                '}';
    }
}

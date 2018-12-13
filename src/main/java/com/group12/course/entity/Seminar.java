package com.group12.course.entity;

import java.time.LocalDateTime;

/**
 * 讨论课实体
 * @author Y Jiang
 * @date  2018/12/13
 */
public class Seminar {
    private Long id;

    /**
     * 一节讨论课属于一个课程
     */
    private Course course;

    /**
     * 一节讨论课存在一轮下
     */
    private Round round;

    private String seminarName;

    private String introduction;

    private Integer maxTeam;

    private Boolean visible;

    private Integer seminarSerial;

    private LocalDateTime enrollStartTime;

    private LocalDateTime enrollEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getSeminarSerial() {
        return seminarSerial;
    }

    public void setSeminarSerial(Integer seminarSerial) {
        this.seminarSerial = seminarSerial;
    }

    public LocalDateTime getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(LocalDateTime enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public LocalDateTime getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(LocalDateTime enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    @Override
    public String toString() {
        return "id:"+this.getId()+"\n"+
        "courseId:" +this.getCourse().getId()+"\n"+
        "roundId:"  +this.getRound().getId()+"\n"+
        "seminarName:"+this.getSeminarName()+"\n"+
        "introduction:"+this.getIntroduction()+"\n"+
        "maxTeam:"+this.getMaxTeam()+"\n"+
        "visible:"+this.getVisible()+"\n"+
        "seminarSerial:"+this.getSeminarSerial()+"\n"+
        "enrollStartTime:"+this.getEnrollStartTime()+"\n"+
        "enrollEndTime:"+this.getEnrollEndTime();

    }
}
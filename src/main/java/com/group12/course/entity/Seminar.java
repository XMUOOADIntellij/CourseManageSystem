package com.group12.course.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Seminar {
    private Long id;

    private Long courseId;

    private Long roundId;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName == null ? null : seminarName.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }

    public Boolean getIsVisible() {
        return visible;
    }

    public void setIsVisible(Boolean visible) {
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
        "courseId:" +this.getCourseId()+"\n"+
        "roundId:"  +this.getRoundId()+"\n"+
        "seminarName:"+this.getSeminarName()+"\n"+
        "introduction:"+this.getIntroduction()+"\n"+
        "maxTeam:"+this.getMaxTeam()+"\n"+
        "visible:"+this.getIsVisible()+"\n"+
        "seminarSerial:"+this.getSeminarSerial()+"\n"+
        "enrollStartTime:"+this.getEnrollStartTime()+"\n"+
        "enrollEndTime:"+this.getEnrollEndTime();

    }
}
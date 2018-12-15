package com.group12.course.vo;

import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;

import java.time.LocalDateTime;

public class SeminarVo {

    /**
     * Seminar 属性
     */
    private Long id;

    private String seminarName;

    private String introduction;

    private Integer maxTeam;

    private Boolean visible;

    private Integer seminarSerial;

    private LocalDateTime enrollStartTime;

    private LocalDateTime enrollEndTime;

    /**
     * 增加属性
     * round 标识课程下的讨论课
     * reportDdl、seminarStatus 标识班级的讨论课
     */
    private Integer round;

    private LocalDateTime reportDdl;

    /**
     * 班级论课所处状态，未开始0，正在进行1，已结束2，暂停3
     */
    private Integer seminarStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public LocalDateTime getReportDdl() {
        return reportDdl;
    }

    public void setReportDdl(LocalDateTime reportDdl) {
        this.reportDdl = reportDdl;
    }

    public Integer getSeminarStatus() {
        return seminarStatus;
    }

    public void setSeminarStatus(Integer seminarStatus) {
        this.seminarStatus = seminarStatus;
    }


    public SeminarVo(){};

    public SeminarVo(KlassSeminar klassSeminar){
        //班级讨论课属性
        this.id = klassSeminar.getId();
        this.reportDdl = klassSeminar.getReportDdl();
        this.seminarStatus = klassSeminar.getSeminarStatus();

        //课程讨论课属性
        Seminar seminar =klassSeminar.getSeminar();
        this.seminarName =seminar.getSeminarName();
        this.introduction = seminar.getIntroduction();
        this.maxTeam = seminar.getMaxTeam();
        this.visible = seminar.getVisible();
        this.seminarSerial=seminar.getSeminarSerial();
        this.enrollStartTime = seminar.getEnrollStartTime();
        this.enrollEndTime = seminar.getEnrollEndTime();

        //课程讨论课所属round
        this.round = seminar.getRound().getRoundSerial();
    }

}

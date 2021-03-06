package com.group12.course.controller.vo;

import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 讨论课实体
 * @author Y Jiang
 * @date 2018/12/22
 */
public class SeminarVO {

    /**
     * id属性
     */
    private Long seminarId;

    /**
     * Seminar 属性
     */
    @NotEmpty(message="讨论课名称不能为空")
    private String seminarName;

    @NotNull(message="需要最大报名组数")
    private Integer maxTeam;

    @NotNull(message="需要设置讨论课是否可见")
    private Boolean visible;

    private String introduction;

    private Integer seminarSerial;

    private LocalDateTime enrollStartTime;

    private LocalDateTime enrollEndTime;

    private Long courseId;

    private Long roundId;


    /**
     *     班级下讨论课属性
     *     status 班级论课所处状态，未开始0，正在进行1，已结束2，暂停3
     */
    private LocalDateTime reportDdl;

    private Integer seminarStatus;

    private Long klassId;

    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
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

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public SeminarVO(){}

    public SeminarVO(KlassSeminar klassSeminar){

        //班级讨论课属性
        this.reportDdl = klassSeminar.getReportDdl();
        this.seminarStatus = klassSeminar.getSeminarStatus();
        this.klassId = klassSeminar.getKlass().getId();

        //课程讨论课属性
        Seminar seminar =klassSeminar.getSeminar();
        this.seminarId = seminar.getId();
        this.seminarName =seminar.getSeminarName();
        this.introduction = seminar.getIntroduction();
        this.maxTeam = seminar.getMaxTeam();
        this.visible = seminar.getVisible();
        this.seminarSerial=seminar.getSeminarSerial();
        this.enrollStartTime = seminar.getEnrollStartTime();
        this.enrollEndTime = seminar.getEnrollEndTime();
    }

    public SeminarVO(Seminar seminar){
        this.seminarId = seminar.getId();
        this.seminarName =seminar.getSeminarName();
        this.introduction = seminar.getIntroduction();
        this.maxTeam = seminar.getMaxTeam();
        this.visible = seminar.getVisible();
        this.seminarSerial=seminar.getSeminarSerial();
        this.enrollStartTime = seminar.getEnrollStartTime();
        this.enrollEndTime = seminar.getEnrollEndTime();

        this.setCourseId(seminar.getCourse().getId());
        this.setRoundId(seminar.getRound().getId());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

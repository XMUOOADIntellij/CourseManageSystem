package com.group12.course.controller.vo;

import com.group12.course.entity.SeminarScore;

import java.math.BigDecimal;


/**
 * 讨论课分数VO
 * @author Y Jiang
 * @date 2018/12/22
 */
public class SeminarScoreVO {

    private  String seminarName;
    private  Long seminarId;
    private  Long classId;

    private Long teamId;

    private BigDecimal reportScore;
    private BigDecimal presentationScore;
    private BigDecimal questionScore;
    private BigDecimal totalScore;


    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }

    public BigDecimal getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(BigDecimal presentationScore) {
        this.presentationScore = presentationScore;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public SeminarScoreVO(){}
    public SeminarScoreVO(SeminarScore seminarScore){
        this.seminarId = seminarScore.getKlassSeminar().getSeminar().getId();
        this.seminarName = seminarScore.getKlassSeminar().getSeminar().getSeminarName();
        this.teamId = seminarScore.getTeam().getId();
        this.classId = seminarScore.getKlassSeminar().getKlass().getId();
        this.presentationScore = seminarScore.getPresentationScore();
        this.questionScore = seminarScore.getQuestionScore();
        this.reportScore = seminarScore.getReportScore();
        this.totalScore =seminarScore.getTotalScore();


    }

    @Override
    public String toString() {
        return super.toString();
    }
}

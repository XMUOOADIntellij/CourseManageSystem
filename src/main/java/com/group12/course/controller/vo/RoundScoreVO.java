package com.group12.course.controller.vo;


import com.group12.course.entity.RoundScore;

import java.math.BigDecimal;


/**
 * 轮次分数VO
 * @author Y Jiang
 * @date 2018/12/22
 */
public class RoundScoreVO {

    private Long roundId;
    private Integer roundSerial;

    private Long teamId;
    private Integer teamSerial;

    private Integer classSerial;

    private BigDecimal reportScore;
    private BigDecimal presentationScore;
    private BigDecimal questionScore;
    private BigDecimal totalScore;

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public Integer getClassSerial() {
        return classSerial;
    }

    public void setClassSerial(Integer classSerial) {
        this.classSerial = classSerial;
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

    public RoundScoreVO(){}

    public RoundScoreVO(RoundScore roundScore){
        this.roundSerial = roundScore.getRound().getRoundSerial();
        this.classSerial = roundScore.getTeam().getKlass().getKlassSerial();
        this.teamSerial  = roundScore.getTeam().getTeamSerial();

        this.presentationScore = roundScore.getPresentationScore();
        this.questionScore = roundScore.getQuestionScore();
        this.reportScore = roundScore.getReportScore();
        this.totalScore =roundScore.getTotalScore();

        this.roundId = roundScore.getRound().getId();
        this.teamId = roundScore.getTeam().getId();
    }
}

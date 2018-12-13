package com.group12.course.entity;


import java.math.BigDecimal;

/**
 * 讨论课分数
 * @author Y Jiang
 * @date 2018/12/12
 */
public class SeminarScore {

    /**
     * 一个分数对应一节班级讨论课
     */
    private KlassSeminar klassSeminar;
    /**
     * 一次分数对应一个队伍
     */
    private Team team;

    private BigDecimal presentationScore;
    private BigDecimal questionScore;
    private BigDecimal reportScore;
    private BigDecimal totalScore;

    public KlassSeminar getKlassSeminar() {
        return klassSeminar;
    }

    public void setKlassSeminar(KlassSeminar klassSeminar) {
        this.klassSeminar = klassSeminar;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }


}

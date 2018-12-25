package com.group12.course.entity;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 轮次分数实体
 * @author Y Jiang
 * @date 2018/12/8
 */
@Alias("roundScore")
public class RoundScore {

    private  Round round;

    private  Team team;

    private BigDecimal totalScore;

    private BigDecimal presentationScore;

    private BigDecimal questionScore;

    private BigDecimal reportScore;

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(BigDecimal presentationScore) {
        this.presentationScore = presentationScore;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }
}
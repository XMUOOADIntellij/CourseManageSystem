package com.group12.course.entity;


import java.math.BigDecimal;

/**
 * 讨论课分数
 * @author Y Jiang
 * @date 2018/12/12
 */
public class SeminarScore {
    private Long klassSeminarId;
    private Long teamId;
    private BigDecimal presentationScore;
    private BigDecimal questionScore;
    private BigDecimal reportScore;
    private BigDecimal totalScore;

    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public Long getTeamId() {
        return teamId;
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

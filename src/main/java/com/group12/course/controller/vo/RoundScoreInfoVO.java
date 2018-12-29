package com.group12.course.controller.vo;

import java.math.BigDecimal;

public class RoundScoreInfoVO {

    private BigDecimal presentationScore;

    private BigDecimal questionScore;

    private BigDecimal reportScore;

    @Override
    public String toString() {
        return "RoundScoreInfoVO{" +
                "presentationScore=" + presentationScore +
                ", questionScore=" + questionScore +
                ", reportScore=" + reportScore +
                '}';
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
}

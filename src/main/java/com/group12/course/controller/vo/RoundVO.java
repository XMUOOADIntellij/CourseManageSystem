package com.group12.course.controller.vo;

import com.group12.course.entity.Round;

public class RoundVO {

    private Integer presentationScoreMethod;

    private Integer reportScoreMethod;

    private Integer questionScoreMethod;

    @Override
    public String toString() {
        return "RoundVO{" +
                "presentationScoreMethod=" + presentationScoreMethod +
                ", reportScoreMethod=" + reportScoreMethod +
                ", questionScoreMethod=" + questionScoreMethod +
                '}';
    }

    public Integer getPresentationScoreMethod() {
        return presentationScoreMethod;
    }

    public void setPresentationScoreMethod(Integer presentationScoreMethod) {
        this.presentationScoreMethod = presentationScoreMethod;
    }

    public Integer getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(Integer reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public Integer getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(Integer questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }

    public RoundVO(){}

    public RoundVO(Round round){
        this.presentationScoreMethod = round.getPresentationScoreMethod();
        this.reportScoreMethod = round.getReportScoreMethod();
        this.questionScoreMethod = round.getQuestionScoreMethod();
    }
}

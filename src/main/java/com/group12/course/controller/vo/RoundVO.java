package com.group12.course.controller.vo;

import com.group12.course.entity.Round;

import javax.validation.constraints.NotEmpty;

/**
 * @author Tan Xue
 */
public class RoundVO {

    @NotEmpty(message="展示分计算方法不能为空")
    private Integer presentationScoreMethod;

    @NotEmpty(message="报告分计算方法不能为空")
    private Integer reportScoreMethod;

    @NotEmpty(message="提问分计算方法名称不能为空")
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

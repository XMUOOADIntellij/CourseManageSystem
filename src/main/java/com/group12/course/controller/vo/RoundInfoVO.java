package com.group12.course.controller.vo;

import com.group12.course.entity.Round;

/**
 * @author Tan Xue
 */
public class RoundInfoVO {

    private Long id;

    private Long courseId;

    private Integer roundSerial;

    private Integer presentationScoreMethod;

    private Integer reportScoreMethod;

    private Integer questionScoreMethod;



    public RoundInfoVO(Round round){
        this.id = round.getId();
        this.courseId = round.getCourse().getId();
        this.roundSerial = round.getRoundSerial();
        this.presentationScoreMethod = round.getPresentationScoreMethod();
        this.questionScoreMethod = round.getQuestionScoreMethod();
        this.reportScoreMethod = round.getReportScoreMethod();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
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

    @Override
    public String toString() {
        return "RoundInfoVO{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", roundSerial=" + roundSerial +
                ", presentationScoreMethod=" + presentationScoreMethod +
                ", reportScoreMethod=" + reportScoreMethod +
                ", questionScoreMethod=" + questionScoreMethod +
                '}';
    }
}

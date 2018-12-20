package com.group12.course.entity;

import com.group12.course.vo.RoundVO;

import java.io.Serializable;

/**
 * Round 实体对象
 * @author Tan Xue
 * @date 2018/12/12
 */
public class Round implements Serializable {
    private Long id;

    private Integer roundSerial;

    private Integer presentationScoreMethod;

    private Integer reportScoreMethod;

    private Integer questionScoreMethod;

    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Round(){

    }

    public Round(RoundVO roundVO){
        this.presentationScoreMethod = roundVO.getPresentationScoreMethod();
        this.reportScoreMethod = roundVO.getReportScoreMethod();
        this.questionScoreMethod = roundVO.getQuestionScoreMethod();
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", roundSerial=" + roundSerial +
                ", presentationScoreMethod=" + presentationScoreMethod +
                ", reportScoreMethod=" + reportScoreMethod +
                ", questionScoreMethod=" + questionScoreMethod +
                ", course=" + course +
                '}';
    }
}
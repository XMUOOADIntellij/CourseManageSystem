package com.group12.course.entity;

public class Round {
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
    public Round(Long id) {
        this.id = id;
    }
}
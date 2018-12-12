package com.group12.course.entity;

public class Round {
    private Long id;

    private Long courseId;

    private Boolean roundSerial;

    private Boolean presentationScoreMethod;

    private Boolean reportScoreMethod;

    private Boolean questionScoreMethod;

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

    public Boolean getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Boolean roundSerial) {
        this.roundSerial = roundSerial;
    }

    public Boolean getPresentationScoreMethod() {
        return presentationScoreMethod;
    }

    public void setPresentationScoreMethod(Boolean presentationScoreMethod) {
        this.presentationScoreMethod = presentationScoreMethod;
    }

    public Boolean getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(Boolean reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public Boolean getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(Boolean questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }
}
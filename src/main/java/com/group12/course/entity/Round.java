package com.group12.course.entity;

public class Round {
    private Long roundId;

    private Short roundNum;

    private Long courseId;

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Short getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(Short roundNum) {
        this.roundNum = roundNum;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
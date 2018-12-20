package com.group12.course.controller.vo;

public class TeamValidApplicationVO {

    private Long id;

    private Long courseId;

    private Long classId;

    private Long teamId;

    private Long leaderId;

    private String reason;

    public TeamValidApplicationVO() {
    }

    public TeamValidApplicationVO(Long id, Long courseId, Long classId, Long teamId, Long leaderId, String reason) {
        this.id = id;
        this.courseId = courseId;
        this.classId = classId;
        this.teamId = teamId;
        this.leaderId = leaderId;
        this.reason = reason;
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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

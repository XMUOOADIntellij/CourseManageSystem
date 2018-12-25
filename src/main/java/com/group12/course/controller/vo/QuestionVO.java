package com.group12.course.controller.vo;

import com.group12.course.entity.*;

import java.math.BigDecimal;


/**
 * 提问 VO
 * @author Y Jiang
 * @date 2018/12/12
 */
public class QuestionVO {
    private Long id;

    private Integer klassSerial;

    private Long attendanceId;

    private Long teamId;
    private Integer teamSerial;

    private String studentName;
    private String account;

    private Boolean selected;

    private BigDecimal score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public QuestionVO(){}

    public QuestionVO(Question question){
        this.id = question.getId();
        this.klassSerial = question.getKlassSeminar().getKlass().getKlassSerial();
        this.account = question.getStudent().getAccount();
        this.studentName = question.getStudent().getStudentName();
        this.teamId =question.getTeam().getId();
        this.teamSerial = question.getTeam().getTeamSerial();
        this.attendanceId = question.getAttendance().getId();
        this.score = question.getScore();
        this.selected = question.getSelected();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

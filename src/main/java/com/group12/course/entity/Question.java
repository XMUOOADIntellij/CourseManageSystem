package com.group12.course.entity;

import com.group12.course.controller.vo.QuestionVO;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Alias("question")
public class Question {
    private Long id;

    /**
     * 一个问题对应一节班级讨论课
     */
    private KlassSeminar klassSeminar;

    /**
     * 一个问题对应一个被提问的报名小组
     */
    private Attendance attendance;

    /**
     * 提问者对应一个小组
     */
    private Team team;

    /**
     * 一个问题对应一个提问的学生
     */
    private Student student;

    private Boolean selected;

    private BigDecimal score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KlassSeminar getKlassSeminar() {
        return klassSeminar;
    }

    public void setKlassSeminar(KlassSeminar klassSeminar) {
        this.klassSeminar = klassSeminar;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    @Override
    public String toString() {
        return
        "id"+id+"\n"+
        "klassSeminar"+klassSeminar.getId()+"\n"+
        "attendance"+attendance.getId()+"\n"+
        "team"+team.getId()+"\n"+
        "student"+student.getId()+"\n"+
        "selected"+selected+"\n"+
        "score"+score+"\n";
    }

    public Question(){}

    public Question(QuestionVO questionVO){
        this.attendance =new Attendance();
        this.attendance.setId(questionVO.getAttendanceId());

        this.score = questionVO.getScore();
    }
}
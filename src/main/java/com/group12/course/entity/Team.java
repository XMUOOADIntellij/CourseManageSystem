package com.group12.course.entity;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Team 实体对象
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class Team {
    private Long id;

    private Course course;

    private Klass klass;

    private Student leader;

    private String teamName;

    private Integer status;

    private Integer teamSerial;

    private List<Student> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Student getLeader() {
        return leader;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }
}
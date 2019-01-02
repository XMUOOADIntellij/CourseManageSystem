package com.group12.course.controller.vo;

import java.io.Serializable;
import java.util.List;

import com.group12.course.entity.Student;
import com.group12.course.entity.Team;

/**
 *  team vo 对象
 *
 * @author Xu Gang
 * @date 2018年12月26日
 */
public class TeamVO  {

    private String name;

    private Long courseId;

    private Long classId;

    private Student leader;

    private List<Student> members;

    private Integer status;

    private Integer teamSerial;

    private Integer klassSerial;

    public TeamVO() {
    }

    public TeamVO(Team team) {
        this.name = team.getTeamName();
        this.leader = team.getLeader();
        this.members = team.getMembers();
        this.status = team.getStatus();
        this.teamSerial = team.getTeamSerial();
        this.klassSerial = team.getKlassSerial();
    }

    @Override
    public String toString() {
        return "TeamVO{" +
                "name='" + name + '\'' +
                ", courseId=" + courseId +
                ", classId=" + classId +
                ", leader=" + leader +
                ", members=" + members +
                ", status=" + status +
                ", teamSerial=" + teamSerial +
                ", klassSerial=" + klassSerial +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
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

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }
}

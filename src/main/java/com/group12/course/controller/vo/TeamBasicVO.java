package com.group12.course.controller.vo;

import com.group12.course.entity.Student;
import com.group12.course.entity.Team;

import java.util.List;

/**
 * @author Tan Xue
 */
public class TeamBasicVO {

    private Long id;

    private String name;

    private Long courseId;

    private Long klassId;

    private Student leader;

    private List<Student> members;

    private Integer status;

    private Integer teamSerial;

    private Integer klassSerial;

    public TeamBasicVO(Team team) {
        this.id = team.getId();
        this.name = team.getTeamName();
        this.courseId = team.getCourse().getId();
        this.klassId = team.getKlass().getId();
        this.leader = team.getLeader();
        this.members = team.getMembers();
        this.status = team.getStatus();
        this.teamSerial = team.getTeamSerial();
        this.klassSerial = team.getKlassSerial();
    }

    @Override
    public String toString() {
        return "TeamBasicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                ", klassId=" + klassId +
                ", leader=" + leader +
                ", members=" + members +
                ", status=" + status +
                ", teamSerial=" + teamSerial +
                ", klassSerial=" + klassSerial +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
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

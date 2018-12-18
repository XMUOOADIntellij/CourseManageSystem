package com.group12.course.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group12.course.entity.Course;
import com.group12.course.entity.Klass;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import com.group12.course.vo.StudentVO;

public class TeamVO implements Serializable {

    private String name;

    private Long courseId;

    private Long classId;

    private Student leader;

    private List<Student> members;

    private Integer status;

    private Integer teamSerial;

    public TeamVO() {
    }

    public TeamVO(Team team) {
        this.name = team.getTeamName();
        this.leader = team.getLeader();
        this.members = team.getMembers();
        this.status = team.getStatus();
        this.teamSerial = team.getTeamSerial();
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
}

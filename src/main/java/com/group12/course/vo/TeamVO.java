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

    private Course course;

    private StudentVO leader;

    private Klass klass;

    private List<Student> members;

    private Integer status;

    private Integer teamSerial;

    public TeamVO() {
    }

    public TeamVO(Team team) {
        this.name = team.getTeamName();
        this.course = team.getCourse();
        this.klass = team.getKlass();
        this.courseId=this.course.getId();
        this.classId = this.klass.getId();
        this.leader = new StudentVO(team.getLeader());
        this.members = team.getMembers();
        this.status = team.getStatus();
        this.teamSerial = team.getTeamSerial();
    }

//    public String toJSON(){
//        Map map = new HashMap(16);
//    }

    @Override
    public String toString() {
        return "TeamVO{" +
                "name='" + name + '\'' +
                ", courseId=" + courseId +
                ", classId=" + classId +
                ", course=" + course +
                ", klass=" + klass +
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

    public StudentVO getLeader() {
        return leader;
    }

    public void setLeader(StudentVO leader) {
        this.leader = leader;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
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

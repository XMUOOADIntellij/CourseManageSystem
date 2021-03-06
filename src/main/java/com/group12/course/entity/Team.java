package com.group12.course.entity;

import com.group12.course.controller.vo.TeamVO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Team 实体对象
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class Team implements Serializable {
    private Long id;

    private Course course;

    private Klass klass;

    private Student leader;

    private String teamName;

    /**
     * status 代表队伍状态，
     * 0为审核中
     * 1为合法
     * 2为不合法
     * 默认为 1 合法
     * */
    private Integer status;

    private Integer teamSerial;

    private Integer klassSerial;

    private List<Student> members;

    public Team() {
        this.status=0;
    }

    public Team(TeamVO teamVO) {
        Course tempCourse=new Course();
        tempCourse.setId(teamVO.getCourseId());
        Klass tempKlass=new Klass();
        tempKlass.setId(teamVO.getClassId());
        this.course=tempCourse;
        this.teamName=teamVO.getName();
        this.klass=tempKlass;
        this.leader=teamVO.getLeader();
        this.members=teamVO.getMembers();
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", course=" + course +
                ", klass=" + klass +
                ", leader=" + leader +
                ", teamName='" + teamName + '\'' +
                ", status=" + status +
                ", teamSerial=" + teamSerial +
                ", members=" + members +
                '}';
    }

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

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }
}
package com.group12.course.controller.vo;

import com.group12.course.entity.Student;
import com.group12.course.entity.Team;

import java.util.List;

public class TeamInfoVO {

    private String name;

    private Student leader;

    private List<Student> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TeamInfoVO{" +
                "name='" + name + '\'' +
                ", leader=" + leader +
                ", members=" + members +
                '}';
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

    public TeamInfoVO(Team team){
        this.name = team.getTeamName();
        this.leader = team.getLeader();
        this.members = team.getMembers();
    }

}

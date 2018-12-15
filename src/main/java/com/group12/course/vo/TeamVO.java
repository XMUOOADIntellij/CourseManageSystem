package com.group12.course.vo;

import java.util.List;
import com.group12.course.vo.StudentVO;

public class TeamVO {

    private String name;

    private Long courseId;

    private Long classId;

    private StudentVO leader;

    private List<StudentVO> members;

    @Override
    public String toString() {
        return "TeamVO{" +
                "name='" + name + '\'' +
                ", courseId=" + courseId +
                ", classId=" + classId +
                ", leader=" + leader +
                ", members=" + members +
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

    public List<StudentVO> getMembers() {
        return members;
    }

    public void setMembers(List<StudentVO> members) {
        this.members = members;
    }

}

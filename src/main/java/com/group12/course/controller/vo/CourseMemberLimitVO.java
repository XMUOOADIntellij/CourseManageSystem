package com.group12.course.controller.vo;

import com.group12.course.entity.Course;

public class CourseMemberLimitVO {

    private Course course;

    private Integer maxMember;

    private Integer minMember;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    @Override
    public String toString() {
        return "CourseMemberLimitVO{" +
                "course=" + course +
                ", maxMember=" + maxMember +
                ", minMember=" + minMember +
                '}';
    }
}

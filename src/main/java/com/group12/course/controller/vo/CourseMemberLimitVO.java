package com.group12.course.controller.vo;


/**
 * @author Tan Xue
 */
public class CourseMemberLimitVO {

    private Long courseId;

    private Integer maxMember;

    private Integer minMember;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
                "course=" + courseId +
                ", maxMember=" + maxMember +
                ", minMember=" + minMember +
                '}';
    }
}

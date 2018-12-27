package com.group12.course.entity.strategy;

import com.group12.course.controller.vo.CourseMemberLimitVO;
import com.group12.course.entity.Course;

/**
 * 选某课程人数限制策略
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class CourseMemberLimitStrategy extends Strategy {

    private Course course;

    private Integer minMember;

    private Integer maxMember;

    public CourseMemberLimitStrategy() {
    }

    public CourseMemberLimitStrategy(Long id, Course course, Integer minMember, Integer maxMember) {
        super(id);
        this.course = course;
        this.minMember = minMember;
        this.maxMember = maxMember;
    }

    public CourseMemberLimitStrategy(CourseMemberLimitVO courseMemberLimitVO){
        this.course = courseMemberLimitVO.getCourse();
        this.minMember = courseMemberLimitVO.getMinMember();
        this.maxMember = courseMemberLimitVO.getMaxMember();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    @Override
    public String toString() {
        return "CourseMemberLimitStrategy{" +
                "course=" + course +
                ", minMember=" + minMember +
                ", maxMember=" + maxMember +
                '}';
    }
}

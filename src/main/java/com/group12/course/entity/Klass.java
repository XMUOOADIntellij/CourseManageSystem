package com.group12.course.entity;

import org.springframework.stereotype.Component;

/**
 * Klass 实体对象
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class Klass {
    private Long id;

    private Course course;

    private Long grade;

    private Integer klassSerial;

    private String introduction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Long courseId) {
        this.course = course;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}
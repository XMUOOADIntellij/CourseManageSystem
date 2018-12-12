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

    private Long courseId;

    private Byte grade;

    private Byte klassSerial;

    private String introduction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Byte getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Byte klassSerial) {
        this.klassSerial = klassSerial;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}
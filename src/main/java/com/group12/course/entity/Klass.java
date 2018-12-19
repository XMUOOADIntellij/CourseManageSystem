package com.group12.course.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Klass 实体对象
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class Klass implements Serializable{
    private Long id;

    private Course course;

    private Long grade;

    private Integer klassSerial;

    private String klassTime;

    private String klassLocation;

    @Override
    public String toString() {
        return "Klass{" +
                "id=" + id +
                ", course=" + course +
                ", grade=" + grade +
                ", klassSerial=" + klassSerial +
                ", klassTime='" + klassTime + '\'' +
                ", klassLocation='" + klassLocation + '\'' +
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

    public String getKlassTime() {
        return klassTime;
    }

    public void setKlassTime(String klassTime) {
        this.klassTime = klassTime;
    }

    public String getKlassLocation() {
        return klassLocation;
    }

    public void setKlassLocation(String klassLocation) {
        this.klassLocation = klassLocation;
    }
}
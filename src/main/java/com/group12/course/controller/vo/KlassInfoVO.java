package com.group12.course.controller.vo;

import com.group12.course.entity.Klass;

/**
 * @author Tan Xue
 */
public class KlassInfoVO {

    private Long id;

    private Long courseId;

    private Long grade;

    private Integer klassSerial;

    private String klassTime;

    private String klassLocation;

    public KlassInfoVO(Klass klass){
        this.id = klass.getId();
        this.courseId = klass.getCourse().getId();
        this.grade = klass.getGrade();
        this.klassSerial = klass.getKlassSerial();
        this.klassTime = klass.getKlassTime();
        this.klassLocation = klass.getKlassLocation();
    }

    @Override
    public String toString() {
        return "KlassInfoVO{" +
                "id=" + id +
                ", courseId=" + courseId +
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

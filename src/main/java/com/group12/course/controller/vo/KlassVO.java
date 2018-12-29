package com.group12.course.controller.vo;

import javax.validation.constraints.NotEmpty;

/**
 * @author Tan Xue
 */
public class KlassVO {

    @NotEmpty(message="年级不能为空")
    private Long grade;

    @NotEmpty(message="班级序号不能为空")
    private Integer klassSerial;

    @NotEmpty(message="上课时间不能为空")
    private String klassTime;

    @NotEmpty(message="上课地点不能为空")
    private String klassLocation;

    @Override
    public String toString() {
        return "KlassVO{" +
                "grade=" + grade +
                ", klassSerial=" + klassSerial +
                ", klassTime='" + klassTime + '\'' +
                ", klassLocation='" + klassLocation + '\'' +
                '}';
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

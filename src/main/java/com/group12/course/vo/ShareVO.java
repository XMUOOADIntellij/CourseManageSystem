package com.group12.course.vo;

import com.group12.course.entity.Teacher;

public class ShareVO {

    private String CourseName;
    private String teacherName;
    private String type;
    private String situation;

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "shareMessageVo{" +
                "CourseName='" + CourseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", type='" + type + '\'' +
                ", situation='" + situation + '\'' +
                '}';
    }
}

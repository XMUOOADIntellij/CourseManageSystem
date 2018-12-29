package com.group12.course.controller.vo;

/**
 * @author Tan Xue
 */
public class ShareVO {

    private String courseName;
    private String teacherName;
    private String type;
    private String situation;

    public String getcourseName() {
        return courseName;
    }

    public void setcourseName(String courseName) {
        courseName = courseName;
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
                "courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", type='" + type + '\'' +
                ", situation='" + situation + '\'' +
                '}';
    }
}

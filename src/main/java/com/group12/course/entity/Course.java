package com.group12.course.entity;

/**
 * @author Y Jiang
 * 2018/11/28
 */
public class Course {

    private String id;
    private String name;
    private String teacherName;
    private String location;

    public Course(){}
    public Course(String id, String name, String teacherName, String location) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.location = location;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getteacherName() {
        return teacherName;
    }
    public void setteacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
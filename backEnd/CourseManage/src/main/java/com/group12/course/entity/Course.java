package com.group12.course.entity;

public class Course {

    private String id;				//课程id
    private String name;			//课程名称
    private String teacher_name;	//教师名称
    private String location;		//上课地点
    public Course(){}
    public Course(String id, String name, String teacher_name, String location) {
        this.id = id;
        this.name = name;
        this.teacher_name = teacher_name;
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
    public String getTeacher_name() {
        return teacher_name;
    }
    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
package com.group12.course.entity;

/**
 * Class 实体对象
 * @author Tan Xue
 * @date 2018/11/30
 */

public class Class {
    private String id;
    private String name;
    private String time;
    private String location;

    public Class() {
        this.id = null;
        this.name = null;
        this.time = null;
        this.location = null;
    }

    public Class(String id, String name, String time, String location) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}
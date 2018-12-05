package com.group12.course.entity;

/**
 * Group 实体对象
 * @author Tan Xue
 * 2018年12月1日
 */
public class Group {
    private String id;

    private String groupNum;

    private String name;

    private Long courseId;

    private String leaderId;

    private Boolean isValid;

    public Group(){
        this.isValid = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum == null ? null : groupNum.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId == null ? null : leaderId.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", groupNum='" + groupNum + '\'' +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                ", leaderId='" + leaderId + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
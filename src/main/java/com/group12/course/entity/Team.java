package com.group12.course.entity;

/**
 * @author Tan Xue
 * 2018年12月1日
 */
public class Team {
    private String id;
    private String name;

    /**
     * 0 表示无效，1 表示有效
     */
    private Integer isValid;

    public Team(){}

    public Team(String id, String name, int isValid) {
        this.id = id;
        this.name = name;
        this.isValid = isValid;
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

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}

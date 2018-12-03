package com.group12.course.entity;


/**
 * @author Y Jiang
 * @date  2018/12/3
 */
public class Teacher extends User {
    private Long teacherNum;

    public Long getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Long teacherNum) {
        this.teacherNum = teacherNum;
    }
}

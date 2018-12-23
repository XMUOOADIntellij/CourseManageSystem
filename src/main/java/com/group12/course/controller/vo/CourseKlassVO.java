package com.group12.course.controller.vo;

import com.group12.course.entity.Course;

/**
 * @author Tan Xue
 * @date 2018/12/19
 */
public class CourseKlassVO {

    private Long id;

    private boolean sharedTeam;

    private boolean sharedSeminar;

    private String name;

    private Long grade;

    private Integer klassSerial;

    private Long klassId;

    private String teacherName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getSharedTeam() {
        return sharedTeam;
    }

    public void setSharedTeam(boolean sharedTeam) {
        sharedTeam = sharedTeam;
    }

    public boolean getSharedSeminar() {
        return sharedSeminar;
    }

    public void setSharedSeminar(boolean sharedSeminar) {
        sharedSeminar = sharedSeminar;
    }

    public String getName() {
        return name;
    }

    public void setCourseName(String name) {
        this.name = name;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Integer getKlassSerial(){
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial){
        this.klassSerial = klassSerial;
    }


    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseKlassVO(Course course){
        this.id = course.getId();
        this.name = course.getCourseName();
        this.teacherName = course.getTeacher().getTeacherName();
        if (course.getTeamMainCourseId()!=null){
            this.setSharedTeam(true);
        }
        else{
            this.setSharedTeam(false);
        }
        if(course.getSeminarMainCourseId()!=null){
            this.setSharedSeminar(true);
        }
        else{
            this.setSharedSeminar(false);
        }
    }

}

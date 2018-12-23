package com.group12.course.entity.application;

import com.group12.course.entity.Course;
import com.group12.course.entity.Teacher;

public class ShareTeamApplication extends Application {

    private Course mainCourse;

    private Course subCourse;

    private Teacher subCourseTeacher;

    public Course getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(Course mainCourse) {
        this.mainCourse = mainCourse;
    }

    public Course getSubCourse() {
        return subCourse;
    }

    public void setSubCourse(Course subCourse) {
        this.subCourse = subCourse;
    }

    public Teacher getSubCourseTeacher() {
        return subCourseTeacher;
    }

    public void setSubCourseTeacher(Teacher subCourseTeacher) {
        this.subCourseTeacher = subCourseTeacher;
    }
}

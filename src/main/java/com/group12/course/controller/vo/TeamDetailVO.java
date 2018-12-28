package com.group12.course.controller.vo;

import com.group12.course.entity.Student;

import java.util.List;

public class TeamDetailVO {

    private String name;

    private Long courseId;

    private Long classId;

    private Student leader;

    private List<Student> members;

    private Integer status;
}

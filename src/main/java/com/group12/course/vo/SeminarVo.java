package com.group12.course.vo;

import com.group12.course.entity.Course;
import com.group12.course.entity.Round;

import java.time.LocalDateTime;

public class SeminarVo {

    /**
     * Seminar 属性
     */
    private Long id;

    private String seminarName;

    private String introduction;

    private Integer maxTeam;

    private Boolean visible;

    private Integer seminarSerial;

    private LocalDateTime enrollStartTime;

    private LocalDateTime enrollEndTime;

    /**
     * 增加属性
     * round 标识课程下的讨论课
     * class 标识班级的讨论课
     */
    private Integer round;

    private Integer klass;

}

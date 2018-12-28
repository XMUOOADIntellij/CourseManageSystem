package com.group12.course.controller.vo;

import com.group12.course.entity.application.ShareSeminarApplication;

public class ShareSeminarApplicationVO {

    private Long id;

    private Long mainCourseId;

    private Long subCourseId;

    private Long subCourseTeacherId;

    private Integer status;

    public ShareSeminarApplicationVO(ShareSeminarApplication shareSeminarApplication){
        this.id = shareSeminarApplication.getId();
        this.mainCourseId = shareSeminarApplication.getMainCourse().getId();
        this.subCourseId = shareSeminarApplication.getSubCourse().getId();
        this.subCourseTeacherId = shareSeminarApplication.getSubCourseTeacher().getId();
        this.status = shareSeminarApplication.getStatus();
    }
}

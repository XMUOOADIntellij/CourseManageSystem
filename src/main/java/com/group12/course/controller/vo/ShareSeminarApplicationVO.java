package com.group12.course.controller.vo;

import com.group12.course.entity.application.ShareSeminarApplication;

/**
 * @author Tan Xue
 */
public class ShareSeminarApplicationVO {

    private Long id;

    private Long mainCourseId;

    private Long subCourseId;

    private Long subCourseTeacherId;

    private Integer status;

    @Override
    public String toString() {
        return "ShareSeminarApplicationVO{" +
                "id=" + id +
                ", mainCourseId=" + mainCourseId +
                ", subCourseId=" + subCourseId +
                ", subCourseTeacherId=" + subCourseTeacherId +
                ", status=" + status +
                '}';
    }

    public ShareSeminarApplicationVO(ShareSeminarApplication shareSeminarApplication){
        this.id = shareSeminarApplication.getId();
        this.mainCourseId = shareSeminarApplication.getMainCourse().getId();
        this.subCourseId = shareSeminarApplication.getSubCourse().getId();
        this.subCourseTeacherId = shareSeminarApplication.getSubCourseTeacher().getId();
        this.status = shareSeminarApplication.getStatus();
    }
}

package com.group12.course.controller.vo;

import com.group12.course.entity.application.ShareTeamApplication;

/**
 * @author Tan Xue
 */
public class ShareTeamApplicationVO {

    private Long id;

    private Long mainCourseId;

    private Long subCourseId;

    private Long subCourseTeacherId;

    private Integer status;

    public ShareTeamApplicationVO(ShareTeamApplication shareTeamApplication){
        this.id = shareTeamApplication.getId();
        this.mainCourseId = shareTeamApplication.getMainCourse().getId();
        this.subCourseId = shareTeamApplication.getSubCourse().getId();
        this.subCourseTeacherId= shareTeamApplication.getSubCourseTeacher().getId();
        this.status = shareTeamApplication.getStatus();
    }

    @Override
    public String toString() {
        return "ShareTeamApplicationVO{" +
                "id=" + id +
                ", mainCourseId=" + mainCourseId +
                ", subCourseId=" + subCourseId +
                ", subCourseTeacherId=" + subCourseTeacherId +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainCourseId() {
        return mainCourseId;
    }

    public void setMainCourseId(Long mainCourseId) {
        this.mainCourseId = mainCourseId;
    }

    public Long getSubCourseId() {
        return subCourseId;
    }

    public void setSubCourseId(Long subCourseId) {
        this.subCourseId = subCourseId;
    }

    public Long getSubCourseTeacherId() {
        return subCourseTeacherId;
    }

    public void setSubCourseTeacherId(Long subCourseTeacherId) {
        this.subCourseTeacherId = subCourseTeacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

package com.group12.course.entity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Y Jiang
 * @date 2018/12/1
 */

public class Seminar {
    private Long id;

    private String theme;

    private String content;

    private Integer serial;

    private Integer maxTeam;

    private Boolean ordered;

    private Boolean visible;

    private LocalDateTime enrollStartDate;

    private LocalDateTime  enrollEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Integer getMaxteam() {
        return maxTeam;
    }

    public void setMaxteam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }

    public Boolean getIsordered() {
        return ordered;
    }

    public void setIsordered(Boolean ordered) {
        this.ordered = ordered;
    }

    public Boolean getIsVisible() {
        return visible;
    }

    public void setIsVisible(Boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime  getEnrollStartDate() {
        return enrollStartDate;
    }

    public void setEnrollStartDate(LocalDateTime  enrollStartDate) {
        this.enrollStartDate = enrollStartDate;
    }

    public LocalDateTime  getEnrollEndDate() {
        return enrollEndDate;
    }

    public void setEnrollEndDate(LocalDateTime  enrollEndDate) {
        this.enrollEndDate = enrollEndDate;
    }
}
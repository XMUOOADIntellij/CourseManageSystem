package com.group12.course.dto;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KlassSeminarDto {

    private Long id;

    //private Seminar seminar;

    private LocalDateTime reportDdl;

    private String seminarStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReportDdl() {
        return reportDdl;
    }

    public void setReportDdl(LocalDateTime reportDdl) {
        this.reportDdl = reportDdl;
    }

    public String getSeminarStatus() {
        return seminarStatus;
    }

    public void setSeminarStatus(String seminarStatus) {
        this.seminarStatus = seminarStatus;
    }
}

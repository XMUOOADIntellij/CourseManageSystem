package com.group12.course.entity;

import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


/**
 * 班级讨论课
 * @author Y Jiang
 * @date 2018/12/13
 */

@Alias("klassSeminar")
public class KlassSeminar {
    private Long id;

    private Klass klass;

    private Seminar seminar;

    private LocalDateTime reportDdl;

    private String seminarStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
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

    public KlassSeminar() {}

    public KlassSeminar(Long id, LocalDateTime reportDdl, String seminarStatus) {
        this.id = id;
        this.reportDdl = reportDdl;
        this.seminarStatus = seminarStatus;
    }

    @Override
    public String toString() {
        return
        "id:"+id+"\n"+
        "klass:"+klass+"\n"+
        "seminar:"+seminar+"\n"+
        "reportDdl:"+reportDdl+"\n"+
        "seminarStatus:"+seminarStatus;
    }
}
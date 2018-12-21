package com.group12.course.entity;

import com.group12.course.controller.vo.SeminarVO;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 班级讨论课
 * @author Y Jiang
 * @date 2018/12/13
 */

@Alias("klassSeminar")
public class KlassSeminar  implements Serializable{
    private Long id;

    private Klass klass;

    private Seminar seminar;

    private LocalDateTime reportDdl;

    /**
     * 论课所处状态，未开始0，正在进行1，已结束2，暂停3
     */
    private Integer seminarStatus;

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

    public Integer getSeminarStatus() {
        return seminarStatus;
    }

    public void setSeminarStatus(Integer seminarStatus) {
        this.seminarStatus = seminarStatus;
    }

    public KlassSeminar() {}

    public  KlassSeminar(SeminarVO seminarVo){
        this.seminarStatus = seminarVo.getSeminarStatus();
        this.reportDdl=seminarVo.getReportDdl();
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
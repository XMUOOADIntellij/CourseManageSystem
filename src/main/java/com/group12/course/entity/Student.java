package com.group12.course.entity;

import org.springframework.stereotype.Component;

import java.util.Date;


public class Student {
    private Long account;

    private String password;

    private String email;

    private Boolean active;

    private Boolean inTeam;

    private Integer notifyInterval;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getInTeam() {
        return inTeam;
    }

    public void setInTeam(Boolean inTeam) {
        this.inTeam = inTeam;
    }

    public Integer getNotifyInterval() {
        return notifyInterval;
    }

    public void setNotifyInterval(Integer notifyInterval) {
        this.notifyInterval = notifyInterval;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
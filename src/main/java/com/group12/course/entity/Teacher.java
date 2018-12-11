package com.group12.course.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Teacher 实体对象
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class Teacher {
    private String account;

    private String password;

    private String email;

    private String name;

    private Boolean active;

    private Integer notifyInterval;

    private Date gmtCreate;

    private Date gmtModified;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
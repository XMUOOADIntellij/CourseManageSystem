package com.group12.course.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Student 实体对象
 * 因为要存入 redis 中
 * 实现了 serializable 接口
 *
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class Student implements Serializable {
    private Long id;

    private String account;

    private String password;

    private String email;

    /**
     * false 代表男，true 代表女
     */
    private Boolean sex;

    private Boolean active;

    private String studentName;

    private Date gmtCreate;

    private Date gmtModified;

    public Student() {
    }

    public Student(String account) {
        this.account = account;
    }

    public Student(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Student(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
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
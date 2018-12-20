package com.group12.course.entity;

import com.group12.course.vo.TeacherVO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Teacher 实体对象
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class Teacher implements Serializable {
    private Long id;

    private String account;

    private String password;

    private String email;

    private String teacherName;

    private Boolean active;

    public Teacher() {
    }

    public Teacher(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Teacher(String account) {
        this.account = account;
    }

    public Teacher(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Teacher(TeacherVO teacherVO){
        this.id=teacherVO.getId();
        this.account=teacherVO.getAccount();
        this.email=teacherVO.getEmail();
        this.teacherName=teacherVO.getName();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", active=" + active +
                '}';
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
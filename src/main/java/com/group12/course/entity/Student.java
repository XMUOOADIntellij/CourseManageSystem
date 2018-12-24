package com.group12.course.entity;

import com.group12.course.controller.vo.StudentVO;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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

    private Boolean active;

    private String studentName;

    public Student() {
        active = false;
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

    public Student(StudentVO studentVO){
        this.id=studentVO.getId();
        this.account=studentVO.getAccount();
        this.studentName=studentVO.getName();
        this.email=studentVO.getEmail();
        this.active=true;
    }

    public Student(Long id, String account, String password, String email, Boolean active, String studentName) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
        this.active = active;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", studentName='" + studentName + '\'' +
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
}
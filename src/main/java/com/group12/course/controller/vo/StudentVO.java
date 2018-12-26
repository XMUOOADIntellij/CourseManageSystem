package com.group12.course.controller.vo;

import com.group12.course.entity.Student;

/**
 * student vo
 *
 * @author Xu Gang
 * @date 2018年12月26日
 */
public class StudentVO {

    private Long id;

    private String account;

    private String name;

    private String email;

    @Override
    public String toString() {
        return "StudentVO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public StudentVO(Student student) {
        this.id=student.getId();
        this.account=student.getAccount();
        this.email=student.getEmail();
        this.name=student.getStudentName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

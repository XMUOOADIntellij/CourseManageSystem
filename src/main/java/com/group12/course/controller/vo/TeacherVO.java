package com.group12.course.controller.vo;

public class TeacherVO {
    private Long id;

    private String account;

    private String email;

    private String name;

    public TeacherVO() {
    }

    public TeacherVO(Long id, String account, String email, String name) {
        this.id = id;
        this.account = account;
        this.email = email;
        this.name = name;
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
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

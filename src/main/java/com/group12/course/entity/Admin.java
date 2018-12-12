package com.group12.course.entity;

import org.springframework.stereotype.Component;

/**
 * Admin 对象
 * @author Xu Gang
 * @date 2018年12月12日
 * */
@Component
public class Admin {
    private Long id;

    private String account;

    private String password;

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
}
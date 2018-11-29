/*
* @author XuGang
 */

package com.group12.course.entity;

public class User{

    private String account;
    private String password;
    private String email;
    private String name;

    /**
     * 0代表未激活，1代表激活
      */
    private int active;

    public User(){}

    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.active = 0;
    }

    public User(String account, String password, String email, String name) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.active=0;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }

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
        this.password = password;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
package com.group12.course.entity;

/**
 * User 实体对象
 * @author Xu Gang
 * @date 2018年11月29日
 */

public class User{

    private String account;
    private String password;
    private String email;
    private String name;

    /**
     * 0代表未激活，1代表激活
      */
    private Integer active;

    public User(String account, String password, String email, String name) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.active = new Integer(0);
    }

    public User(String account, String password) {
        this(account,password,"","");
    }

    public User(){
        this("","");
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

    public Integer getActive() {
        return active==null?0:active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
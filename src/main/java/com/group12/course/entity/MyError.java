package com.group12.course.entity;

/**
 * @author Y Jiang
 * 2018/11/28
 */
public class MyError {
    private int code;
    private String message;
    public MyError(int code,String message){
        this.code=code;
        this.message=message;
    }
    public int getCode(){return code;}
    public String getMessage(){return message;}
}


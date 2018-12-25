package com.group12.course.exception;


/**
 * 所需的信息不符合异常
 * @author Y Jiang
 * @date 2018/12/25
 */
public class InformationException extends RuntimeException{
    public InformationException(String message) {
        super(message);
    }
}

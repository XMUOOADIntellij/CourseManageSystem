package com.group12.course.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 所需的信息不符合异常
 * 错误码：411
 * @author Y Jiang
 * @date 2018/12/25
 */
@ResponseStatus(HttpStatus.LENGTH_REQUIRED)
public class InformationException extends RuntimeException{
    public InformationException(String message) {
        super(message);
    }
}

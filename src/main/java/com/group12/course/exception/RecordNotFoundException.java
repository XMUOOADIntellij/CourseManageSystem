package com.group12.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常
 * Source not found when it is requested by it's ID
 * @author Y Jiang
 * @date 2018/12/24
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String exception){
        super(exception);
    }
}

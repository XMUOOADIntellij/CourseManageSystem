package com.group12.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常
 * source be operated unauthorized
 * @author Y Jiang
 * @date 2018/12/24
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedOperationException extends RuntimeException {

    public UnauthorizedOperationException(String message) {
        super(message);
    }
}

package com.group12.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 队伍在审核中的异常
 * 错误码 409
 *
 * @author Xu Gang
 * @date 2018年12月27日
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class TeamInAuditingException extends RuntimeException{
    public TeamInAuditingException(String errorMessage){
        super(errorMessage);
    }
}

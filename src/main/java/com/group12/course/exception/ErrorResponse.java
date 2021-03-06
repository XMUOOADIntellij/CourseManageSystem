package com.group12.course.exception;


import java.util.List;


/**
 * 错误返回的实体
 * @author Y Jiang
 * @date  2018/12/25
 */
public class ErrorResponse {

    /**
     * general error message about nature of error
     */
    private String message;

    /**
     * specific errors in api request processing
     */
    private List<String> details;

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}

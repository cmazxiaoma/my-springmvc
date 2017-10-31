package com.augmentum.exam.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -4209015477480534763L;
    private String code;
    private String message;

    public ServiceException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message) {
        super();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.github.hesimin.springboot.common;

/**
 * 业务异常
 *
 * @author hesimin 2017-04-18
 */
public class ServiceException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public ServiceException setCode(String code) {
        this.code = code;
        return this;
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

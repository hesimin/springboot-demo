package com.github.hesimin.springboot.common;

/**
 * 共通返回结果
 * @author hesimin 2017-04-18
 */
public class Result {
    private boolean success;
    private String  code;
    private String  message;
    private Object  data;

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}

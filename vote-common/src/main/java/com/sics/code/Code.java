package com.sics.code;

/**
 * 该类用来描述状态码信息
 *
 * @author 44380
 * @version 2022~07~27~0:25
 */
public enum Code {
    SUCCESS(200,"success"),
    FAIL(400,"fail");

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private int code;
    private String message;

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
package com.sics.constant.enums;

/**
 * 该类用来描述状态码信息
 *
 * @author 44380
 * @version 2022~07~27~0:25
 */
public enum Code {
    // success
    SUCCESS(200,"success!"),
    REPEAT(201,"You have voted!"),
    // fail
    FAIL(400,"fail!");

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private final int code;
    private final String message;

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
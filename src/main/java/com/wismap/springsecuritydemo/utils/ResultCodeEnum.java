package com.wismap.springsecuritydemo.utils;

public enum ResultCodeEnum {

    SUCCESS(200, "successful"),
    REDIRECT(301, "redirect"),
    Forbidden(403,"Forbidden"),
    NOT_FOUND(404, "not found"),
    SERVER_ERROR(500,"server error")

    ;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

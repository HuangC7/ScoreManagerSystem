package com.hnchances.hyx.commom;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/1
 * Description:
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "成功"),

    /* 错误状态码 */
    BIZ_ERROR(400,"业务异常"),
    NOT_FOUND(404, "请求的资源不存在"),
    INTERNAL_ERROR(500, "接口异常"),
    ;

    private Integer code;
    private String message;

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
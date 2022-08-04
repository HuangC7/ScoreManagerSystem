package com.hnchances.hyx.Exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hnchances.hyx.commom.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/1
 * Description:异常响应的API统一返回体
 */
@Data
public class ErrorResult implements Serializable {
    private Integer code;
    private String msg;
    private boolean success = false;
    @JsonIgnore
    private ResultCode resultCode;

    public static ErrorResult error() {
        ErrorResult result = new ErrorResult();
        result.setResultCode(ResultCode.INTERNAL_ERROR);
        return result;
    }

    /**
     * 接口异常
     *
     */
    public static ErrorResult error(String message) {
        ErrorResult result = new ErrorResult();
        result.setCode(ResultCode.INTERNAL_ERROR.code());
        result.setMsg(message);
        return result;
    }


    public static ErrorResult error(Integer code, String message) {
        ErrorResult result = new ErrorResult();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    public static ErrorResult error(ResultCode resultCode, String message) {
        ErrorResult result = new ErrorResult();
        result.setResultCode(resultCode);
        result.setMsg(message);
        return result;
    }
}
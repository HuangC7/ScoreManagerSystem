package com.hnchances.hyx.commom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(description = "返回结果类")
@Data
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息内容")
    private String msg;
    @ApiModelProperty(value = "状态判断")
    private boolean success = true;
    @ApiModelProperty(value = "返回结果")
    private T result;
    @JsonIgnore
    private ResultCode resultCode;

    private Result() {
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.msg = resultCode.message();
        this.result = data;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setResult(data);
        return result;
    }
}

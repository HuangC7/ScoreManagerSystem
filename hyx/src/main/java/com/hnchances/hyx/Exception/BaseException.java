package com.hnchances.hyx.Exception;

import com.hnchances.hyx.commom.ResultCode;
import lombok.Data;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/1
 * Description:自定义异常基类
 */
@Data
public class BaseException extends RuntimeException {

    private static final int BASE_EXCEPTION_CODE = ResultCode.INTERNAL_ERROR.code();
    private static final String BASE_EXCEPTION_MESSAGE = ResultCode.INTERNAL_ERROR.message();


    private Integer code;
    private String message;

    public BaseException() {
        super(BASE_EXCEPTION_MESSAGE);
        this.code = BASE_EXCEPTION_CODE;
        this.message = BASE_EXCEPTION_MESSAGE;
    }

    public BaseException(String message) {
        super(message);
        this.code = BASE_EXCEPTION_CODE;
        this.message = message;
    }

    public BaseException(ResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.code = BASE_EXCEPTION_CODE;
        this.message = BASE_EXCEPTION_MESSAGE;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = BASE_EXCEPTION_CODE;
        this.message = message;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
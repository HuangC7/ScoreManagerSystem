package com.hnchances.hyx.Exception;

import com.hnchances.hyx.commom.ResultCode;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/1
 * Description:自定义业务异常类
 */
public class BizException extends BaseException {

    public BizException(String message) {
        this.setCode(400);
        this.setMessage(message);
    }
}

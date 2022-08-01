package com.hnchances.hyx.commom;

import lombok.Data;

public class Result <T> {

    private int code;       // 状态码
    private String msg;     // 返回的信息
    private T data;         // 返回的数据

    /**
     * 成功时候的调用（有数据）
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 成功时候的调用（无数据）
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(){
        return new Result<T>();
    }

    private Result(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    private Result() {
        this.code = 200;
        this.msg = "success";
    }

    /**
     * 异常时候的调用（固定参数）
     * @param error
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Error error){
        return new Result<T>(error);
    }

    private Result(Error error) {
        if (error == null){
            return ;
        }
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

}

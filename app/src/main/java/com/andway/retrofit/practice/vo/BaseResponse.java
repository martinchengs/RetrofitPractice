package com.andway.retrofit.practice.vo;

/**
 * 这个类需要根据用户需要自行修改
 * Created by andway on 16/1/25.
 */
public class BaseResponse<T> {

    private int errcode;

    private String msg;

    private T data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return getErrcode() == 0;
    }
}

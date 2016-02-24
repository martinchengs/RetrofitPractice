package com.andway.retrofit.practice.listener;


import com.andway.retrofit.practice.vo.BaseResponse;

/**
 * Created by WayneSuper on 26/01/2016.
 */
public interface IHttpAsyncHandler<T> {

    void onResponse(BaseResponse<T> response);

    void onFailure(String error);
}

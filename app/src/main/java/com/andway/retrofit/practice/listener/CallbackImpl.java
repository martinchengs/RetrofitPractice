package com.andway.retrofit.practice.listener;

import com.andway.retrofitlibrary.response.BaseResponse;
import com.andway.retrofitlibrary.util.NetworkUtil;

import retrofit2.Response;

/**
 * Created by WayneSuper on 27/01/2016.
 */
public class CallbackImpl<T> implements retrofit2.Callback<BaseResponse<T>> {
    IHttpAsyncHandler<T> listener;

    public CallbackImpl(IHttpAsyncHandler<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Response<BaseResponse<T>> response) {
        handResponse(listener, response);
    }

    @Override
    public void onFailure(Throwable t) {
        handFailure(listener);
    }

    private <T> void handFailure(IHttpAsyncHandler<T> listener) {
        if (listener == null) return;
        if (!NetworkUtil.hasInternet()) listener.onFailure("未发现网络连接");
        else {
            listener.onFailure("网络请求异常");
        }
    }

    private <T> void handResponse(IHttpAsyncHandler<T> listener, Response<BaseResponse<T>> response) {
        if (listener == null) return;
        if (response.isSuccess() && response.body().getErrcode() == 0) {
            listener.onResponse(response.body());
        } else {
            if (response.isSuccess()) {
                processResponse(response.body());
                listener.onFailure(response.body().getMsg());
            } else {
                listener.onFailure(response.message());
            }
            listener.onFailure(response.isSuccess() ? response.body().getMsg() : response.message());
        }
    }

    /**
     * 这里可以统一处理返回结果，比如token过期
     *
     * @param body
     * @param <T>
     */
    protected <T> void processResponse(BaseResponse<T> body) {

    }
}

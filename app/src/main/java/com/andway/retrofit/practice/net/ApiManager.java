package com.andway.retrofit.practice.net;

import com.andway.retrofit.practice.listener.CallbackImpl;
import com.andway.retrofit.practice.listener.IHttpAsyncHandler;
import com.andway.retrofit.practice.vo.BaseResponse;

import java.io.Serializable;

import retrofit2.Call;

/**
 * Created by WayneSuper on 26/01/2016.
 */
public class ApiManager implements Serializable {
    private Api mApi;

    public ApiManager() {
        mApi = HttpService.instance().shareApiManager();
    }

    private static class Holder {
        static final ApiManager INSTANCE = new ApiManager();
    }

    public static final ApiManager instance() {
        return Holder.INSTANCE;
    }

    public Call<BaseResponse<String>> test(String api, final IHttpAsyncHandler<String> listener) {
        Call<BaseResponse<String>> call = mApi.test(api);
        call.enqueue(new CallbackImpl(listener));
        return call;
    }
}

package com.andway.retrofit.practice.net;

import com.andway.retrofit.practice.vo.BaseResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by WayneSuper on 24/02/2016.
 */
public interface Api {
    @FormUrlEncoded
    @POST("/api/auth/{api}")
   Call<BaseResponse<String>> test(@Path("api") String api);


}

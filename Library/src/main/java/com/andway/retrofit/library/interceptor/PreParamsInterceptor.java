package com.andway.retrofit.library.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public abstract class PreParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl.Builder urlBuilder = original.url().newBuilder();
        /**这里可以添加共有的请求参数*/
        urlBuilder = getPreQueryParameters(urlBuilder);
        HttpUrl url = urlBuilder.build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url)
                .method(original.method(), original.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    public abstract HttpUrl.Builder getPreQueryParameters(HttpUrl.Builder builder);
}
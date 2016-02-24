package com.andway.retrofit.practice.net;


import com.andway.retrofit.library.converter.FastJsonConverterFactory;
import com.andway.retrofit.library.interceptor.LoggingInterceptor;
import com.andway.retrofit.library.interceptor.PreParamsInterceptor;
import com.andway.retrofit.practice.BuildConfig;

import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by andway on 16/1/24.
 */
public class HttpService {

    private static HttpService instance;
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private final Api mAPi;

    private HttpService() {
        ///这个是用来打印日志的
        LoggingInterceptor interceptor = new LoggingInterceptor();
        interceptor.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(new ParamsInterceptor());
        httpClient.addInterceptor(interceptor);
        httpClient.cookieJar(new SJCookieJar()); //添加一次请求缓存
        OkHttpClient client = httpClient.build();

        Retrofit.Builder build = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(FastJsonConverterFactory.create());
        Retrofit retrofit = build.client(client).build();
        mAPi = retrofit.create(Api.class);
    }

    public static HttpService instance() {
        if (instance == null) {
            synchronized (HttpService.class) {
                if (instance == null) {
                    instance = new HttpService();
                }
            }
        }
        return instance;
    }

    public Api shareApiManager() {
        return mAPi;
    }


    private class SJCookieJar implements CookieJar {
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            UrlRecorder.getInstance().setCookies(cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = UrlRecorder.getInstance().getCookies();
            if (cookies != null) {
                return cookies;
            }
            return Collections.emptyList();
        }
    }

    private class ParamsInterceptor extends PreParamsInterceptor {

        @Override
        public HttpUrl.Builder getPreQueryParameters(HttpUrl.Builder builder) {
            return builder;
        }
    }

}

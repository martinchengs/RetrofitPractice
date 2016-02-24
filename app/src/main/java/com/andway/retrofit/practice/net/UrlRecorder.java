package com.andway.retrofit.practice.net;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by andway on 16/1/24.
 */
public class UrlRecorder {
    private static UrlRecorder instance;
    private List<Cookie> cookies;
    private UrlRecorder() {

    }

    public static final UrlRecorder getInstance() {
        if (instance == null) {
            synchronized (UrlRecorder.class) {
                if (instance == null) {
                    instance = new UrlRecorder();
                }
            }
        }
        return instance;
    }


    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }
}

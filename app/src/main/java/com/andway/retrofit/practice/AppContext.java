package com.andway.retrofit.practice;

import android.app.Application;

/**
 * Created by WayneSuper on 24/02/2016.
 */
public class AppContext extends Application {
    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static final AppContext app() {
        return instance;
    }
}

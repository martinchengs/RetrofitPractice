package com.andway.retrofit.practice.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.andway.retrofit.practice.AppContext;


/**
 * Created by WayneSuper on 24/02/2016.
 */
public class NetworkUtil {


    public static boolean hasInternet() {
        Context ctx = AppContext.app();
        boolean flag;
        if (((ConnectivityManager) ctx.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }
}

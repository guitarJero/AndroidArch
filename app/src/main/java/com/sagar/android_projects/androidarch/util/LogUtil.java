package com.sagar.android_projects.androidarch.util;


import android.util.Log;

import com.sagar.android_projects.androidarch.BuildConfig;
import com.sagar.android_projects.androidarch.core.Const;

public class LogUtil {
    @SuppressWarnings("unused")
    public static void logV(String message) {
        if (BuildConfig.DEBUG)
            Log.v(Const.LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logD(String message) {
        if (BuildConfig.DEBUG)
            Log.d(Const.LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logI(String message) {
        if (BuildConfig.DEBUG)
            Log.i(Const.LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logW(String message) {
        if (BuildConfig.DEBUG)
            Log.w(Const.LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logE(String message) {
        if (BuildConfig.DEBUG)
            Log.e(Const.LOG_TAG, message);
    }
}

package com.sagar.android_projects.androidarch.util;


import android.util.Log;

import com.sagar.android_projects.androidarch.BuildConfig;

public class LogUtil {
    private static final String LOG_TAG = "Android_Arch_Log";

    @SuppressWarnings("unused")
    public static void logV(String message) {
        if (BuildConfig.DEBUG)
            Log.v(LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logD(String message) {
        if (BuildConfig.DEBUG)
            Log.d(LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logI(String message) {
        if (BuildConfig.DEBUG)
            Log.i(LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logW(String message) {
        if (BuildConfig.DEBUG)
            Log.w(LOG_TAG, message);
    }

    @SuppressWarnings("unused")
    public static void logE(String message) {
        if (BuildConfig.DEBUG)
            Log.e(LOG_TAG, message);
    }
}

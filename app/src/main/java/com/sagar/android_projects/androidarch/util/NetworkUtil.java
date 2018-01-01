package com.sagar.android_projects.androidarch.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * created by SAGAR KUMAR NAYAK
 * class to perform any network operations,
 * <p>
 * exposed methods
 * {@link #isNetworkAvailable(Context)}
 */
public class NetworkUtil {

    /**
     * method to check if the device is connected to internet
     *
     * @param context context
     * @return true if device connected to internet, false otherwise
     */
    @SuppressWarnings({"unused", "SameParameterValue", "WeakerAccesssss"})
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

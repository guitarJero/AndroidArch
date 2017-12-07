package com.sagar.android_projects.androidarch.repository.network.retrofit.interceptor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sagar.android_projects.androidarch.core.Messages;
import com.sagar.android_projects.androidarch.util.LogUtil;
import com.sagar.android_projects.androidarch.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class OfflineResponseCacheInterceptor implements Interceptor {
    private Context context;

    public OfflineResponseCacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (Boolean.valueOf(chain.request().header("cacheResponseOffline"))) {
            LogUtil.logI(Messages.OFFLINE_CACHE_APPLIED);
            if (!NetworkUtil.isNetworkAvailable(context)) {
                request = request.newBuilder()
                        .header("Cache-Control",
                                "public, only-if-cached, max-stale=" + 60)
                        .build();
            }
        } else {
            LogUtil.logI(Messages.OFFLINE_CACHE_NOT_APPLIED);
        }
        return chain.proceed(request);
    }
}
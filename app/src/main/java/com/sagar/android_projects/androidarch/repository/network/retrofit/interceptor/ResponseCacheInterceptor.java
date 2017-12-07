package com.sagar.android_projects.androidarch.repository.network.retrofit.interceptor;

import android.support.annotation.NonNull;

import com.sagar.android_projects.androidarch.core.Messages;
import com.sagar.android_projects.androidarch.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;

public class ResponseCacheInterceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());

        if (Boolean.valueOf(chain.request().header("cacheResponse"))) {
            LogUtil.logI(Messages.CACHE_APPLIED);
            originalResponse = originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 60)
                    .build();
        } else {
            LogUtil.logI(Messages.CACHE_NOT_APPLIED);
        }
        return originalResponse;
    }
}
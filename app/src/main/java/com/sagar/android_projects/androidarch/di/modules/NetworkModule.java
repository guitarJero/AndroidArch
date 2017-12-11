package com.sagar.android_projects.androidarch.di.modules;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sagar.android_projects.androidarch.core.Const;
import com.sagar.android_projects.androidarch.di.scope.AppScope;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;
import com.sagar.android_projects.androidarch.repository.network.retrofit.interceptor.OfflineResponseCacheInterceptor;
import com.sagar.android_projects.androidarch.repository.network.retrofit.interceptor.ResponseCacheInterceptor;
import com.sagar.android_projects.androidarch.util.LogUtil;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @AppScope
    @Provides
    AndroidArchApiInterface androidArchApiInterface(Retrofit retrofit) {
        return retrofit.create(AndroidArchApiInterface.class);
    }

    @AppScope
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @AppScope
    @Provides
    OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                              ResponseCacheInterceptor responseCacheInterceptor,
                              OfflineResponseCacheInterceptor offlineResponseCacheInterceptor,
                              Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(responseCacheInterceptor)
                .addInterceptor(offlineResponseCacheInterceptor)
                .cache(cache)
                .build();
    }

    @AppScope
    @Provides
    HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                LogUtil.logI(message);
            }
        });
    }

    @AppScope
    @Provides
    ResponseCacheInterceptor responseCacheInterceptor() {
        return new ResponseCacheInterceptor();
    }

    @AppScope
    @Provides
    OfflineResponseCacheInterceptor offlineResponseCacheInterceptor(Application application) {
        return new OfflineResponseCacheInterceptor(application);
    }

    @AppScope
    @Provides
    Cache cache(File directory) {
        return new Cache(directory, 5 * 1024 * 1024);
    }

    @AppScope
    @Provides
    File file(Application application) {
        return new File(application.getCacheDir() + "apiResponse");
    }
}

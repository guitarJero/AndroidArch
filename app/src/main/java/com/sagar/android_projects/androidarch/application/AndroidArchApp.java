package com.sagar.android_projects.androidarch.application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;
import com.sagar.android_projects.androidarch.repository.database.UserRoomDatabase;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;
import com.sagar.android_projects.androidarch.repository.network.retrofit.interceptor.OfflineResponseCacheInterceptor;
import com.sagar.android_projects.androidarch.repository.network.retrofit.interceptor.ResponseCacheInterceptor;
import com.sagar.android_projects.androidarch.util.LogUtil;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AndroidArchApp extends Application {

    private UserRoomDatabase userRoomDatabase;
    private AndroidArchRepository androidArchRepository;
    private AndroidArchApiInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                LogUtil.logI(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(new ResponseCacheInterceptor())
                .addInterceptor(new OfflineResponseCacheInterceptor(this))
                .cache(new Cache(new File(getCacheDir(),
                        "apiResponses"), 5 * 1024 * 1024))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(AndroidArchApiInterface.class);

        userRoomDatabase = Room.databaseBuilder(this, UserRoomDatabase.class, UserRoomDatabase.DATABASE_NAME).build();
        androidArchRepository = new AndroidArchRepository(apiInterface,userRoomDatabase);
    }

    public UserRoomDatabase getUserRoomDatabase() {
        return userRoomDatabase;
    }

    public AndroidArchRepository getAndroidArchRepository() {
        return androidArchRepository;
    }

    public AndroidArchApiInterface getApiInterface() {
        return apiInterface;
    }
}

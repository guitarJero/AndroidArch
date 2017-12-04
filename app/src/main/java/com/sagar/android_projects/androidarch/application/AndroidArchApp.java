package com.sagar.android_projects.androidarch.application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.sagar.android_projects.androidarch.constants.Const;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;
import com.sagar.android_projects.androidarch.repository.database.UserRoomDatabase;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;

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

        userRoomDatabase = Room.databaseBuilder(this, UserRoomDatabase.class, UserRoomDatabase.DATABASE_NAME).build();
        androidArchRepository = new AndroidArchRepository();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(Const.LOG_TAG, message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(AndroidArchApiInterface.class);
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

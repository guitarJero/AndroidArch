package com.sagar.android_projects.androidarch.repository.network.retrofit;


import com.sagar.android_projects.androidarch.repository.database.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AndroidArchApiInterface {

    @GET("api/users/{userId}")
    public Observable<UserEntity> getUserDetail(String userId);
}

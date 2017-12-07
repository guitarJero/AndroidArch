package com.sagar.android_projects.androidarch.repository.network.retrofit;


import com.sagar.android_projects.androidarch.pojo.UserData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AndroidArchApiInterface {

    @GET("api/users/{userId}")
    public Observable<UserData> getUserDetail(@Path("userId") String userId,
                                              @Header("cacheResponse") boolean cacheResponse,
                                              @Header("cacheResponseOffline") boolean cacheResponseOffline);
}

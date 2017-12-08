package com.sagar.android_projects.androidarch.repository.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @SuppressWarnings("unused")
    @Query("SELECT * FROM UserDetail")
    Flowable<List<UserEntity>> getAllUser();

    @Query("SELECT * FROM UserDetail WHERE userId = :userId")
    Flowable<UserEntity> getUserDetail(String userId);

    @Insert
    long[] addUser(UserEntity... userEntity);
}

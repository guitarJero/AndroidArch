package com.sagar.android_projects.androidarch.repository.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserDetail")
    public Flowable<List<UserEntity>> getAllUser();

    @Query("SELECT * FROM UserDetail WHERE userId = :userId")
    public Flowable<UserEntity> getUserDetail(String userId);

    @Insert
    public long[] addUser(UserEntity... userEntity);
}

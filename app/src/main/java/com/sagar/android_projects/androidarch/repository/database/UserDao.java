package com.sagar.android_projects.androidarch.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserDetail")
    public LiveData<List<UserEntity>> getAllUser();

    @Query("SELECT * FROM UserDetail WHERE userId = :userId")
    public LiveData<UserEntity> getUserDetail(String userId);

    @Insert
    public long[] addUser(UserEntity... userEntity);
}

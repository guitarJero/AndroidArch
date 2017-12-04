package com.sagar.android_projects.androidarch.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    public ArrayList<LiveData<UserEntity>> getAllUser();

    @Query("SELECT * FROM UserEntity WHERE userId = :userId")
    public LiveData<UserEntity> getUserDetail(String userId);

    @Insert
    public void addUser(UserEntity... userEntity);
}

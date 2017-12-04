package com.sagar.android_projects.androidarch.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "user_database";

    public abstract UserDao userDao();
}

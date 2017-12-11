package com.sagar.android_projects.androidarch.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.sagar.android_projects.androidarch.di.scope.AppScope;
import com.sagar.android_projects.androidarch.repository.database.UserRoomDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @AppScope
    @Provides
    UserRoomDatabase userRoomDatabase(Application application) {
        return Room.databaseBuilder(application,
                UserRoomDatabase.class,
                UserRoomDatabase.DATABASE_NAME)
                .build();
    }

}

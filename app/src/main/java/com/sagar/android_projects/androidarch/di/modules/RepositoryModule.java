package com.sagar.android_projects.androidarch.di.modules;

import com.sagar.android_projects.androidarch.di.scope.AppScope;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;
import com.sagar.android_projects.androidarch.repository.database.UserRoomDatabase;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @AppScope
    @Provides
    AndroidArchRepository androidArchRepository(AndroidArchApiInterface androidArchApiInterface,
                                                UserRoomDatabase userRoomDatabase) {
        return new AndroidArchRepository(androidArchApiInterface, userRoomDatabase);
    }
}

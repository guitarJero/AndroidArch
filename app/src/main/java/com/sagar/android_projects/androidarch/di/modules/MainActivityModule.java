package com.sagar.android_projects.androidarch.di.modules;

import android.databinding.PropertyChangeRegistry;

import com.sagar.android_projects.androidarch.di.scope.MainActivityScope;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;
import com.sagar.android_projects.androidarch.ui.mainactivity.ViewModelProviderMainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @MainActivityScope
    @Provides
    PropertyChangeRegistry propertyChangeRegistry() {
        return new PropertyChangeRegistry();
    }

    @MainActivityScope
    @Provides
    ViewModelProviderMainActivity viewModelProviderMainActivity(AndroidArchRepository androidArchRepository) {
        return new ViewModelProviderMainActivity(androidArchRepository);
    }
}

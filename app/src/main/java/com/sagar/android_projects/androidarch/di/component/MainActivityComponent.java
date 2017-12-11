package com.sagar.android_projects.androidarch.di.component;

import com.sagar.android_projects.androidarch.di.modules.MainActivityModule;
import com.sagar.android_projects.androidarch.di.scope.MainActivityScope;
import com.sagar.android_projects.androidarch.ui.mainactivity.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@MainActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}

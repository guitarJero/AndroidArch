package com.sagar.android_projects.androidarch.di.modules;

import android.app.Activity;

import com.sagar.android_projects.androidarch.di.component.MainActivityComponent;
import com.sagar.android_projects.androidarch.ui.mainactivity.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);
}

package com.sagar.android_projects.androidarch.di.component;

import com.sagar.android_projects.androidarch.di.scope.AppScope;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;

import dagger.Component;

@AppScope
@Component
public interface AppComponent {
    public AndroidArchRepository androidArchRepository();
}

package com.sagar.android_projects.androidarch.di.component;

import android.app.Application;

import com.sagar.android_projects.androidarch.application.AndroidArchApp;
import com.sagar.android_projects.androidarch.di.modules.ActivityBuilder;
import com.sagar.android_projects.androidarch.di.modules.AppModule;
import com.sagar.android_projects.androidarch.di.modules.DatabaseModule;
import com.sagar.android_projects.androidarch.di.modules.NetworkModule;
import com.sagar.android_projects.androidarch.di.modules.RepositoryModule;
import com.sagar.android_projects.androidarch.di.scope.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@AppScope
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityBuilder.class,
        AppModule.class,
        RepositoryModule.class,
        DatabaseModule.class,
        NetworkModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(AndroidArchApp androidArchApp);
}

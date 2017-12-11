package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;

public class ViewModelProviderMainActivity implements ViewModelProvider.Factory {

    private AndroidArchRepository androidArchRepository;

    public ViewModelProviderMainActivity(AndroidArchRepository androidArchRepository) {
        this.androidArchRepository = androidArchRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(androidArchRepository);
        }
        return null;
    }
}

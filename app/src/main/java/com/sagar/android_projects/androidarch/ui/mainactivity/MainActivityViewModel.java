package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.sagar.android_projects.androidarch.application.AndroidArchApp;
import com.sagar.android_projects.androidarch.pojo.Data;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;


public class MainActivityViewModel extends AndroidViewModel {

    AndroidArchRepository repository;

    private MutableLiveData<Data> data;
    private LiveData<Boolean> isDataBeingFetched;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = ((AndroidArchApp) application.getApplicationContext()).getAndroidArchRepository();

        data = new MutableLiveData<>();

        data.setValue(new Data("sagar nayak"));
    }

    public MutableLiveData<Data> getData() {
        return data;
    }

    public LiveData<Boolean> getIsDataBeingFetched() {
        return isDataBeingFetched;
    }
}

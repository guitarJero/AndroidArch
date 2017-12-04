package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.sagar.android_projects.androidarch.pojo.Data;


public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Data> data;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        data = new MutableLiveData<>();

        data.setValue(new Data("sagar nayak"));
    }

    public MutableLiveData<Data> getData() {
        return data;
    }
}

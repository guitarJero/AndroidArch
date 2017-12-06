package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.app.Application;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;


public class MainActivityViewModel extends ViewModel {

    AndroidArchRepository repository;
    Application application;

    private MediatorLiveData<UserDetail> data;
    private MutableLiveData<Boolean> isDataBeingFetched;

    public MainActivityViewModel(Application application, AndroidArchRepository repository) {
        this.repository = repository;
        this.application = application;

        isDataBeingFetched = new MutableLiveData<>();
        isDataBeingFetched.setValue(false);
    }

    private void getUserData(AndroidArchRepository repository, String userId) {
        isDataBeingFetched.setValue(true);
        data.addSource(repository.getUserDetails(userId),
                new Observer<UserDetail>() {
                    @Override
                    public void onChanged(@Nullable UserDetail userDetail) {
                        data.setValue(userDetail);
                        isDataBeingFetched.setValue(false);
                    }
                });
    }

    public MutableLiveData<UserDetail> getData(String userId) {
        data = new MediatorLiveData<>();
        getUserData(repository, userId);
        return data;
    }

    public MutableLiveData<Boolean> getIsDataBeingFetched() {
        return isDataBeingFetched;
    }
}

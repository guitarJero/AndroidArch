package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;
import com.sagar.android_projects.androidarch.util.Response;

public class MainActivityViewModel extends ViewModel {

    private AndroidArchRepository repository;

    private MediatorLiveData<UserDetail> data;
    private MutableLiveData<Boolean> isDataBeingFetched;
    private MutableLiveData<String> queriedUserId;
    private MutableLiveData<UserDetail> userDetailMutableLiveData;

    MainActivityViewModel(AndroidArchRepository repository) {
        this.repository = repository;

        isDataBeingFetched = new MutableLiveData<>();
        isDataBeingFetched.setValue(false);
        data = new MediatorLiveData<>();
        queriedUserId = new MutableLiveData<>();
    }

    MutableLiveData<String> getQueriedUserId() {
        return queriedUserId;
    }

    public MutableLiveData<UserDetail> getData() {
        if (data != null && queriedUserId != null) {
            if (data.getValue() != null &&
                    data.getValue().getResponse() != null &&
                    data.getValue().getResponse() == Response.SUCCESS &&
                    queriedUserId.getValue() != null &&
                    queriedUserId.getValue().length() != 0) {
                if (String.valueOf(data.getValue().getUserEntity().getUserId())
                        .equals(queriedUserId.getValue())) {
                    return data;
                }
            }
        }
        data = new MediatorLiveData<>();
        return data;
    }

    MutableLiveData<Boolean> getIsDataBeingFetched() {
        return isDataBeingFetched;
    }

    void fetchData() {
        if (data != null) {
            getUserData(repository, queriedUserId.getValue());
        }
    }

    private void getUserData(AndroidArchRepository repository, String userId) {
        if (data.getValue() != null &&
                data.getValue().getUserEntity() != null &&
                String.valueOf(data.getValue().getUserEntity().getUserId()).equals(queriedUserId.getValue()))
            return;
        isDataBeingFetched.setValue(true);
        if (userDetailMutableLiveData != null)
            data.removeSource(userDetailMutableLiveData);
        userDetailMutableLiveData = repository.getUserDetails(userId);
        data.addSource(userDetailMutableLiveData,
                new Observer<UserDetail>() {
                    @Override
                    public void onChanged(@Nullable UserDetail userDetail) {
                        isDataBeingFetched.setValue(false);
                        data.setValue(userDetail);
                    }
                });
    }
}

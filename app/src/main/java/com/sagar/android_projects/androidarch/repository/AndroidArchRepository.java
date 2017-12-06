package com.sagar.android_projects.androidarch.repository;


import android.arch.lifecycle.MutableLiveData;

import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.database.UserEntity;
import com.sagar.android_projects.androidarch.util.Response;

public class AndroidArchRepository {
    private MutableLiveData<UserDetail> userDetail;

    public AndroidArchRepository() {
        this.userDetail = new MutableLiveData<>();
    }

    public MutableLiveData<UserDetail> getUserDetails(String userId) {
        userDetail.setValue(new UserDetail(new UserEntity(1, "sagar", "nayak", "avatar"),
                Response.SUCCESS));
        return userDetail;
    }
}

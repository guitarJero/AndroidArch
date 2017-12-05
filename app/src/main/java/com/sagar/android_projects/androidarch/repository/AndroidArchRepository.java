package com.sagar.android_projects.androidarch.repository;


import android.arch.lifecycle.LiveData;

import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.database.UserDao;
import com.sagar.android_projects.androidarch.repository.database.UserEntity;

public class AndroidArchRepository {

    private UserDao userDao;

    public LiveData<UserDetail> getUserDetail(String userId) {
        LiveData<UserEntity> user = userDao.getUserDetail(userId);
        if (user == null)
            getDataFromServer(userId);
    }

}

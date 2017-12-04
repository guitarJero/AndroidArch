package com.sagar.android_projects.androidarch.repository.network.service;

import android.app.IntentService;
import android.content.Intent;

import com.sagar.android_projects.androidarch.repository.database.UserDao;
import com.sagar.android_projects.androidarch.repository.database.UserEntity;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class GetUserDetails extends IntentService {

    public GetUserDetails() {
        super("GetUserDetails");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
        }
    }

    private void getUserDetails(AndroidArchApiInterface androidArchApiInterface, String userId,
                                final UserDao userDao) {
        androidArchApiInterface.getUserDetail(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserEntity userEntity) {
                        userDao.addUser(userEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
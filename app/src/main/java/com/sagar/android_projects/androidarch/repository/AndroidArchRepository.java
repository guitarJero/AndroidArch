package com.sagar.android_projects.androidarch.repository;


import android.arch.lifecycle.MutableLiveData;

import com.sagar.android_projects.androidarch.application.AppExecutors;
import com.sagar.android_projects.androidarch.core.Messages;
import com.sagar.android_projects.androidarch.pojo.UserData;
import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.database.UserEntity;
import com.sagar.android_projects.androidarch.repository.database.UserRoomDatabase;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;
import com.sagar.android_projects.androidarch.util.LogUtil;
import com.sagar.android_projects.androidarch.util.Response;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class AndroidArchRepository {

    private MutableLiveData<UserDetail> userDetail;
    private AndroidArchApiInterface androidArchApiInterface;
    private Disposable disposable;
    private UserRoomDatabase userRoomDatabase;

    public AndroidArchRepository(AndroidArchApiInterface androidArchApiInterface, UserRoomDatabase userRoomDatabase) {
        this.userDetail = new MutableLiveData<>();
        this.androidArchApiInterface = androidArchApiInterface;
        this.userRoomDatabase = userRoomDatabase;
    }

    public MutableLiveData<UserDetail> getUserDetails(String userId) {
        getUserDetailsFromServer(userId);
        return userDetail;
    }

    private void getUserDetailsFromServer(final String userId) {
        androidArchApiInterface.getUserDetail(userId, false, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(UserData user) {
                        LogUtil.logI(Messages.DATA_FROM_SERVER);
                        userDetail.setValue(new UserDetail(user.getUserEntity(), Response.SUCCESS));
                        saveDataToDB(user.getUserEntity());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getDataFromDb(userId);
                        userDetail.setValue(new UserDetail(null, Response.FAILED));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    private void saveDataToDB(final UserEntity userEntity) {
        AppExecutors.getInstance().diskIO().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        userRoomDatabase.userDao().addUser(userEntity);
                    }
                }
        );
    }

    private void getDataFromDb(String userId) {
        userRoomDatabase.userDao().getUserDetail(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<UserEntity>() {
                    @Override
                    public void onNext(UserEntity userEntityData) {
                        if (userEntityData == null) {
                            userDetail.setValue(new UserDetail(null, Response.FAILED));
                        } else {
                            LogUtil.logI(Messages.DATA_FROM_ROOM);
                            userDetail.setValue(new UserDetail(userEntityData, Response.SUCCESS));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        userDetail.setValue(new UserDetail(null, Response.FAILED));
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.logI(" complete");
                    }
                });
    }
}

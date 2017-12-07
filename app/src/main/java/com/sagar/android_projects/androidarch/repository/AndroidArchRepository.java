package com.sagar.android_projects.androidarch.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.sagar.android_projects.androidarch.pojo.UserData;
import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.database.UserEntity;
import com.sagar.android_projects.androidarch.repository.database.UserRoomDatabase;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;
import com.sagar.android_projects.androidarch.util.Response;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    private void saveDataToDB(UserEntity userEntity) {
        Observable.just(userRoomDatabase.userDao().addUser(userEntity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void getDataFromDb(String userId) {
        Observable.just(userRoomDatabase.userDao().getUserDetail(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveData<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LiveData<UserEntity> userEntityLiveData) {
                        if (userEntityLiveData.getValue() == null) {
                            userDetail.setValue(new UserDetail(null, Response.FAILED));
                        } else {
                            userDetail.setValue(new UserDetail(userEntityLiveData.getValue(), Response.SUCCESS));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        userDetail.setValue(new UserDetail(null, Response.FAILED));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

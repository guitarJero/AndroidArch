package com.sagar.android_projects.androidarch.repository;


import android.arch.lifecycle.MutableLiveData;

import com.sagar.android_projects.androidarch.pojo.UserData;
import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.network.retrofit.AndroidArchApiInterface;
import com.sagar.android_projects.androidarch.util.Response;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AndroidArchRepository {

    private MutableLiveData<UserDetail> userDetail;
    private AndroidArchApiInterface androidArchApiInterface;
    private Disposable disposable;

    public AndroidArchRepository(AndroidArchApiInterface androidArchApiInterface) {
        this.userDetail = new MutableLiveData<>();
        this.androidArchApiInterface = androidArchApiInterface;
    }

    public MutableLiveData<UserDetail> getUserDetails(String userId) {
        getUserDetailsFromServer(userId);
        return userDetail;
    }

    private void getUserDetailsFromServer(String userId) {
        androidArchApiInterface.getUserDetail(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(UserData user) {
                        userDetail.setValue(new UserDetail(user.getUserEntity(),Response.SUCCESS));
                    }

                    @Override
                    public void onError(Throwable e) {
                        userDetail.setValue(new UserDetail(null, Response.FAILED));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }
}

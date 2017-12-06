package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sagar.android_projects.androidarch.R;
import com.sagar.android_projects.androidarch.application.AndroidArchApp;
import com.sagar.android_projects.androidarch.constants.Const;
import com.sagar.android_projects.androidarch.databinding.ActivityMainBinding;
import com.sagar.android_projects.androidarch.pojo.UserData;
import com.sagar.android_projects.androidarch.pojo.UserDetail;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private ActivityMainBinding activityMainBinding;

    @SuppressWarnings("FieldCanBeLocal")
    private MainActivityViewModel mainActivityViewModel;
    ViewModelProviderMainActivity viewModelProviderMainActivity;

    private String edittextText;
    public boolean isDataBeingFetched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setContext(this);

        setSupportActionBar(activityMainBinding.toolbar);

        viewModelProviderMainActivity = new ViewModelProviderMainActivity(this.getApplication(),
                ((AndroidArchApp) getApplicationContext()).getAndroidArchRepository());

        mainActivityViewModel = ViewModelProviders.of(this, viewModelProviderMainActivity)
                .get(MainActivityViewModel.class);

        mainActivityViewModel.getIsDataBeingFetched().observe(this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean == null)
                            return;
                        isDataBeingFetched = aBoolean;
                    }
                });

        ((AndroidArchApp)getApplicationContext()).getApiInterface()
                .getUserDetail("2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserData user) {
                        Log.i(Const.LOG_TAG, "onNext: "+user.getUserEntity());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void textChangedInEditText(CharSequence s,
                                      @SuppressWarnings("unused") int start,
                                      @SuppressWarnings("unused") int before,
                                      @SuppressWarnings("unused") int count) {
        edittextText = s.toString();
    }

    public void getUserDetails() {
        if (edittextText == null)
            return;
        if (edittextText.length() == 0)
            return;
        mainActivityViewModel.getData(edittextText).observe(this,
                new Observer<UserDetail>() {
                    @Override
                    public void onChanged(@Nullable UserDetail userDetail) {
                        if (userDetail == null)
                            return;
                        activityMainBinding.setData(userDetail);
                    }
                });
    }
}

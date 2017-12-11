package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sagar.android_projects.androidarch.BR;
import com.sagar.android_projects.androidarch.R;
import com.sagar.android_projects.androidarch.databinding.ActivityMainBinding;
import com.sagar.android_projects.androidarch.pojo.UserDetail;
import com.sagar.android_projects.androidarch.repository.AndroidArchRepository;
import com.sagar.android_projects.androidarch.util.UIUtil;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements Observable {

    @Inject
    AndroidArchRepository androidArchRepository;
    @Inject
    ViewModelProviderMainActivity viewModelProviderMainActivity;
    @Inject
    PropertyChangeRegistry registry;

    @SuppressWarnings("FieldCanBeLocal")
    private ActivityMainBinding activityMainBinding;
    @SuppressWarnings("FieldCanBeLocal")
    private MainActivityViewModel mainActivityViewModel;

    private String edittextText;
    public boolean isDataBeingFetched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setContext(this);

        setSupportActionBar(activityMainBinding.toolbar);

        mainActivityViewModel = ViewModelProviders.of(this, viewModelProviderMainActivity)
                .get(MainActivityViewModel.class);

        mainActivityViewModel.getIsDataBeingFetched().observe(this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean == null)
                            return;
                        isDataBeingFetched = aBoolean;
                        registry.notifyChange(MainActivity.this, BR.dataBeingFetched);
                    }
                });

        mainActivityViewModel.getData().observe(this,
                new Observer<UserDetail>() {
                    @Override
                    public void onChanged(@Nullable UserDetail userDetail) {
                        if (userDetail == null)
                            return;
                        activityMainBinding.setData(userDetail);
                    }
                });

        mainActivityViewModel.getQueriedUserId().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        edittextText = s;
                    }
                });
    }

    public void textChangedInEditText(CharSequence s,
                                      @SuppressWarnings("unused") int start,
                                      @SuppressWarnings("unused") int before,
                                      @SuppressWarnings("unused") int count) {
        edittextText = s.toString();
        mainActivityViewModel.getQueriedUserId().setValue(edittextText);
    }

    public void getUserDetails() {
        if (edittextText == null)
            return;
        if (edittextText.length() == 0)
            return;
        UIUtil.hideSoftKeyboard(this);
        mainActivityViewModel.fetchData();
    }

    @Bindable
    public boolean isDataBeingFetched() {
        return isDataBeingFetched;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("queried_id", edittextText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mainActivityViewModel.getQueriedUserId()
                .setValue(savedInstanceState.getString("queried_id"));
    }
}

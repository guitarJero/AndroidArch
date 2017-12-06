package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sagar.android_projects.androidarch.R;
import com.sagar.android_projects.androidarch.application.AndroidArchApp;
import com.sagar.android_projects.androidarch.databinding.ActivityMainBinding;
import com.sagar.android_projects.androidarch.pojo.UserDetail;

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

        mainActivityViewModel.getData().observe(this,
                new Observer<UserDetail>() {
                    @Override
                    public void onChanged(@Nullable UserDetail userDetail) {
                        if (userDetail == null)
                            return;
                        activityMainBinding.setData(userDetail);
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
        Toast.makeText(this, "the data is " + edittextText, Toast.LENGTH_SHORT).show();
    }
}

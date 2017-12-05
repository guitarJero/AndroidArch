package com.sagar.android_projects.androidarch.ui.mainactivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sagar.android_projects.androidarch.R;
import com.sagar.android_projects.androidarch.databinding.ActivityMainBinding;
import com.sagar.android_projects.androidarch.pojo.Data;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("FieldCanBeLocal")
    private ActivityMainBinding activityMainBinding;

    @SuppressWarnings("FieldCanBeLocal")
    private MainActivityViewModel mainActivityViewModel;

    private String edittextText;
    public boolean isDataBeingFetched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setContext(this);

        setSupportActionBar(activityMainBinding.toolbar);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getData().observe(this,
                new Observer<Data>() {
                    @Override
                    public void onChanged(@Nullable Data data) {
                        activityMainBinding.setData(data);
                    }
                });

        mainActivityViewModel.getIsDataBeingFetched().observe(this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean == null)
                            return;
                        isDataBeingFetched = aBoolean;
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

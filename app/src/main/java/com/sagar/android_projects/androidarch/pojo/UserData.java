package com.sagar.android_projects.androidarch.pojo;

import com.google.gson.annotations.SerializedName;
import com.sagar.android_projects.androidarch.repository.database.UserEntity;

public class UserData {
    @SerializedName("data")
    private UserEntity userEntity;

    @SuppressWarnings("unused")
    public UserData() {
    }

    @SuppressWarnings("unused")
    public UserData(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @SuppressWarnings("unused")
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}

package com.sagar.android_projects.androidarch.pojo;


import com.sagar.android_projects.androidarch.repository.database.UserEntity;
import com.sagar.android_projects.androidarch.util.Response;

public class UserDetail {
    private UserEntity userEntity;
    private Response response;

    public UserDetail() {
    }

    public UserDetail(UserEntity userEntity, Response response) {
        this.userEntity = userEntity;
        this.response = response;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @SuppressWarnings("unused")
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Response getResponse() {
        return response;
    }

    @SuppressWarnings("unused")
    public void setResponse(Response response) {
        this.response = response;
    }
}

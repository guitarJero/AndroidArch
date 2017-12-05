package com.sagar.android_projects.androidarch.pojo;


import com.sagar.android_projects.androidarch.repository.database.UserEntity;

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

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

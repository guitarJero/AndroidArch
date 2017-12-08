package com.sagar.android_projects.androidarch.repository.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "UserDetail")
public class UserEntity {
    @PrimaryKey
    @SerializedName("id")
    private int userId;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("avatar")
    private String avatar;

    @SuppressWarnings("unused")
    @Ignore
    public UserEntity() {
    }

    UserEntity(int userId, String firstName, String lastName, String avatar) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    @SuppressWarnings("unused")
    @Ignore
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings("unused")
    @Ignore
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    @SuppressWarnings("unused")
    @Ignore
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getAvatar() {
        return avatar;
    }

    @SuppressWarnings("unused")
    @Ignore
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

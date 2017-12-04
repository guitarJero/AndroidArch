package com.sagar.android_projects.androidarch.repository.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "UserDetail")
public class UserEntity {
    @PrimaryKey
    private int userId;
    private String firstName;
    private String lastName;
    private String avatar;

    public UserEntity(int userId, String firstName, String lastName, String avatar) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }
}

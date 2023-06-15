package com.example.medic.common;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

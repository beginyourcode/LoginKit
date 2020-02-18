package com.example.loginkit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginResponse {


    @SerializedName("token")
    private String token;
    @SerializedName("results")

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

package com.example.loginkit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Login {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String passsword;

    public Login(String userName, String passsword) {
        this.userName = userName;
        this.passsword = passsword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String isPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
}

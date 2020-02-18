package com.example.loginkit.model;

import com.google.gson.annotations.SerializedName;


public class Register {
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String passsword;

    public Register(String fullname, String email, String passsword) {
        this.fullname = fullname;
        this.email = email;
        this.passsword = passsword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
}

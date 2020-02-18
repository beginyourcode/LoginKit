package com.example.loginkit.model;

import com.google.gson.annotations.SerializedName;


public class ErrorResponse {
    @SerializedName("code")
    private String code;
    @SerializedName("description")
    private String description;

    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

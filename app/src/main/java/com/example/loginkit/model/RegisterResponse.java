package com.example.loginkit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RegisterResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private RegisterResponseData data;

    @SerializedName("error")
    private ErrorResponse error;

    public class RegisterResponseData {
        @SerializedName("email")
        private String email;

        @SerializedName("fullname")
        private String fullname;

        @SerializedName("token")
        private String token;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RegisterResponseData getData() {
        return data;
    }

    public void setData(RegisterResponseData data) {
        this.data = data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
}

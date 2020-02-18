package com.example.loginkit.rest;

import com.example.loginkit.model.Login;
import com.example.loginkit.model.LoginResponse;
import com.example.loginkit.model.Register;
import com.example.loginkit.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiInterface {
    @POST("ApplicationUser/login")
    Call<RegisterResponse> signIn(@Body Login login);

    @POST("ApplicationUser/register")
    Call<RegisterResponse> signUp(@Body Register register);

}

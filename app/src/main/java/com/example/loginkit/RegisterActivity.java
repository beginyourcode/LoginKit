package com.example.loginkit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginkit.model.ErrorResponse;
import com.example.loginkit.model.Register;
import com.example.loginkit.model.RegisterResponse;
import com.example.loginkit.rest.ApiClient;
import com.example.loginkit.rest.ApiInterface;
import com.example.loginkit.sessionManager.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.txt_full_name)
    EditText txt_full_name;
    @BindView(R.id.txt_email)
    EditText txt_email;
    @BindView(R.id.txt_password)
    EditText txt_password;
    @BindView(R.id.txt_confirm_password)
    EditText txt_confirm_password;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_link_to_reset_password)
    void onResetPassword() {
        startActivity(new Intent(RegisterActivity.this, ResetPasswordActivity.class));
    }

    @OnClick(R.id.btn_link_to_login)
    void onSignIn() {
        finish();
    }

    @OnClick(R.id.btn_register)
    void onSignUp() {
        String full_name = txt_full_name.getText().toString().trim();
        String email = txt_email.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        String confirm_password = txt_confirm_password.getText().toString().trim();

        if (TextUtils.isEmpty(full_name)) {
            Toast.makeText(getApplicationContext(), "Enter full name!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(confirm_password)) {
            Toast.makeText(getApplicationContext(), "Enter confirm password!", Toast.LENGTH_SHORT).show();
            return;
        }
        /*else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!password.matches(confirm_password)) {
            Toast.makeText(getApplicationContext(), "Password and confirm password does not match", Toast.LENGTH_SHORT).show();
            return;
        }
        */
        progressBar.setVisibility(View.VISIBLE);
        //create user
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<RegisterResponse> call = apiService.signUp(new Register(full_name, email, password));
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    boolean succeeded = response.body().isSuccess();
                    if (succeeded) {
                        sessionManager.createUserLoginSession(response.body().getData().getFullname(),
                                response.body().getData().getEmail(),
                                response.body().getData().getToken());
                        sessionManager.checkLogin();
                        Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, response.body().getError().getDescription(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Log error here since request failed
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this, "ErrorResponse while downloading database", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
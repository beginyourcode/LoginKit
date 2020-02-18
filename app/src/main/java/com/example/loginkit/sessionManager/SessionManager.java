package com.example.loginkit.sessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.loginkit.BaseActivity;
import com.example.loginkit.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;      // Shared pref mode
    SessionManager sessionManager;
    private static final String PREF_NAME = "Preference";

    private static final String IS_LOGIN = "is_logged_in";
    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_FIREBASE_TOKEN = "firebase_token";
    public static final String KEY_DEVICE_ID = "device_id";
    public static final String KEY_MANUFACTURER = "manufacturer";
    public static final String KEY_MODEL = "model";
    public static final String KEY_FINGERPRINT = "fingerPrint";


    public SessionManager(Context c) {
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String fullname, String email, String token) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_FULL_NAME, fullname);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getUser() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_FULL_NAME, pref.getString(KEY_FULL_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
        return user;
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Intent intent = new Intent(context, BaseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void redirectToLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        // After logout redirect user to Login Activity
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void createUserFireBaseNotificationToken(String Token, String Device_ID, String manufacturer, String Model, String fingerPrint) {
        editor.putString(KEY_FIREBASE_TOKEN, Token);
        editor.putString(KEY_DEVICE_ID, Device_ID);
        editor.putString(KEY_MANUFACTURER, manufacturer);
        editor.putString(KEY_MODEL, Model);
        editor.putString(KEY_FINGERPRINT, fingerPrint);
        editor.commit();
    }

    public HashMap<String, String> getUserFireBaseNotificationToken() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_FIREBASE_TOKEN, pref.getString(KEY_FIREBASE_TOKEN, null));
        user.put(KEY_DEVICE_ID, pref.getString(KEY_DEVICE_ID, null));
        user.put(KEY_MANUFACTURER, pref.getString(KEY_MANUFACTURER, null));
        user.put(KEY_MODEL, pref.getString(KEY_MODEL, null));
        user.put(KEY_FINGERPRINT, pref.getString(KEY_FINGERPRINT, null));

        return user;
    }

    public void updatePassword(String Password) {
        editor.putString(KEY_TOKEN, Password);
        editor.commit();
    }


}

package com.bisu.herbalplantidentification;
//import android.content.Context;
//import android.content.SharedPreferences;
//
//public class SharedPrefManager {
//
//    private static final String SHARED_PREF_NAME = "your_shared_pref_name";
//    private static final String KEY_USER_ID = "user_id";
//    private static final String KEY_USERNAME = "username";
//    private static final String KEY_EMAIL = "email";
//    private static final String KEY_PASSWORD = "pass";
//
//    private static SharedPrefManager instance;
//    private final SharedPreferences sharedPreferences;
//    private final Context context;
//
//    private SharedPrefManager(Context context) {
//        this.context = context;
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//    }
//
//    public static synchronized SharedPrefManager getInstance(Context context) {
//        if (instance == null) {
//            instance = new SharedPrefManager(context);
//        }
//        return instance;
//    }
//
//    public void userLogin(int userId, String username, String pass, String email) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(KEY_USER_ID, userId);
//        editor.putString(KEY_USERNAME, username);
//        editor.putString(KEY_PASSWORD, pass);
//        editor.putString(KEY_EMAIL, email);
//        editor.apply();
//    }
//
//    public boolean isLoggedIn() {
//        return sharedPreferences.getInt(KEY_USER_ID, -1) != -1;
//    }
//
//    public void logout() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//    }
//
//    public String getPass(){
//        return sharedPreferences.getString(KEY_PASSWORD,null);
//    }
//
//    public String getUsername() {
//        return sharedPreferences.getString(KEY_USERNAME, null);
//    }
//
//    public String getEmail() {
//        return sharedPreferences.getString(KEY_EMAIL, null);
//    }
//
//    public Integer getId(){
//        return sharedPreferences.getInt(KEY_USER_ID,-1);
//    }

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "your_shared_pref_name";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    private static SharedPrefManager instance;
    private final SharedPreferences sharedPreferences;
    private final Context context;

    public SharedPrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void userLogin(int userId, String username, String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getInt(KEY_USER_ID, -1) != -1;
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }
    public Integer getId(){return sharedPreferences.getInt(KEY_USER_ID,-1);}

    public String getPass(){
        return sharedPreferences.getString(KEY_PASSWORD,null);
    }
}

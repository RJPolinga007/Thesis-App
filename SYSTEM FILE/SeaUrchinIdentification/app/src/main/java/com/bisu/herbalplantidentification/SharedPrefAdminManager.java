package com.bisu.herbalplantidentification;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefAdminManager {



    private static final String SHARED_PREF_NAME = "your_shared_pref_name_admin";
    private static final String KEY_USER_ID = "sample_id";
    private static final String KEY_USERNAME = "sample_username";
    private static final String KEY_EMAIL = "sample_email";
    private static final String KEY_PASSWORD = "sample_password";


    private static SharedPrefAdminManager instance;
    private final SharedPreferences sharedPreferencesAdmin;
    private final Context context;

    public SharedPrefAdminManager(Context context) {
        this.context = context;
        sharedPreferencesAdmin = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefAdminManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefAdminManager(context);
        }
        return instance;
    }

    public void userLogin(int userId, String username, String email, String password) {
        SharedPreferences.Editor editor = sharedPreferencesAdmin.edit();
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferencesAdmin.getInt(KEY_USER_ID, -1) != -1;
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferencesAdmin.edit();
        editor.clear();
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferencesAdmin.getString(KEY_USERNAME, null);
    }

    public String getEmail() {
        return sharedPreferencesAdmin.getString(KEY_EMAIL, null);
    }
    public Integer getId(){return sharedPreferencesAdmin.getInt(KEY_USER_ID,-1);}

    public String getPass(){
        return sharedPreferencesAdmin.getString(KEY_PASSWORD,null);
    }
}

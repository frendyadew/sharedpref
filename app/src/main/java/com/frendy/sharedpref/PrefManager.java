package com.frendy.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREFS_FILENAME = "AuthAppPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private final SharedPreferences sharedPreferences;
    private static volatile PrefManager instance;

    // Konstruktor
    private PrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
    }

    // Mendapatkan instance PrefManager
    public static PrefManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PrefManager.class) {
                if (instance == null) {
                    instance = new PrefManager(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    // Menyimpan username
    public void saveUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    // Mendapatkan username
    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    // Menyimpan password
    public void savePassword(String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    // Mendapatkan password
    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }

    // Menyimpan status login
    public void setLoggedIn(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    // Mengecek apakah sudah login
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Menghapus semua data
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

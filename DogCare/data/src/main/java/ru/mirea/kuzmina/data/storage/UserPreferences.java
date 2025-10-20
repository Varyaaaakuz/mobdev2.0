package ru.mirea.kuzmina.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    private final SharedPreferences sharedPreferences;

    public UserPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }

    public void saveUserEmail(String email) {
        sharedPreferences.edit().putString("user_email", email).apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString("user_email", null);
    }

    public void clearUserData() {
        sharedPreferences.edit().clear().apply();
    }
}
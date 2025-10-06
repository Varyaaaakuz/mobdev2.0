package ru.mirea.kuzmina.dogcare.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import ru.mirea.kuzmina.dogcare.domain.repository.AuthRepository;

public class AuthRepositoryImpl implements AuthRepository {
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "auth_prefs";

    public AuthRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean login(String email, String password) {
        // Тестовая логика - всегда возвращает true для демонстрации
        sharedPreferences.edit().putBoolean("is_logged_in", true).apply();
        return true;
    }

    @Override
    public boolean register(String email, String password) {
        sharedPreferences.edit().putBoolean("is_logged_in", true).apply();
        return true;
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("is_logged_in", false);
    }

    @Override
    public void logout() {
        sharedPreferences.edit().putBoolean("is_logged_in", false).apply();
    }
}
package ru.mirea.kuzmina.dogcare.domain.repository;

public interface AuthRepository {
    boolean login(String email, String password);
    boolean register(String email, String password);
    boolean isLoggedIn();
    void logout();
}
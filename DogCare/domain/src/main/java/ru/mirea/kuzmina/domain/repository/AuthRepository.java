package ru.mirea.kuzmina.domain.repository;

import ru.mirea.kuzmina.domain.models.User;

public interface AuthRepository {
    User signIn(String email, String password) throws Exception;
    User signUp(String email, String password, String name) throws Exception; // Добавляем name
    void signOut() throws Exception;
    User getCurrentUser();
}
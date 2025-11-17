package ru.mirea.kuzmina.domain.usecases;

import ru.mirea.kuzmina.domain.models.User;
import ru.mirea.kuzmina.domain.repository.AuthRepository;

public class SignInUseCase {
    private final AuthRepository authRepository;

    public SignInUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public User execute(String email, String password) throws Exception {
        return authRepository.signIn(email, password);
    }
    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}
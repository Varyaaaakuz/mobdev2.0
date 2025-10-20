package ru.mirea.kuzmina.dogcare.domain.usecases;

import ru.mirea.kuzmina.dogcare.domain.repository.AuthRepository;

public class LoginUseCase {
    private final AuthRepository authRepository;

    public LoginUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean execute(String email, String password) {
        return authRepository.login(email, password);
    }
}
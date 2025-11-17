package ru.mirea.kuzmina.domain.usecases;

import ru.mirea.kuzmina.domain.models.User;
import ru.mirea.kuzmina.domain.repository.AuthRepository;

public class SignUpUseCase {
    private final AuthRepository authRepository;

    public SignUpUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    // Обновляем метод для приема имени
    public User execute(String email, String password, String name) throws Exception {
        return authRepository.signUp(email, password, name);
    }
}
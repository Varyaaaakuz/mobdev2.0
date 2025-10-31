package ru.mirea.kuzmina.data.repository;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.repository.MLRepository;

public class MLRepositoryImpl implements MLRepository {

    @Override
    public Dog identifyBreed(byte[] imageData) {
        // Заглушка для ML
        return new Dog("1", "Лабрадор", "Дружелюбная семейная собака", "", "Крупная", "Высокая активность");
    }
}
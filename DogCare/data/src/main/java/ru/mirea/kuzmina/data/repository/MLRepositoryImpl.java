package ru.mirea.kuzmina.data.repository;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.repository.MLRepository;

public class MLRepositoryImpl implements MLRepository {

    @Override
    public Dog identifyBreed(byte[] imageData) {
        // Заглушка для ML распознавания породы
        return new Dog("1", "Labrador", "Labrador Retriever", "");
    }
}
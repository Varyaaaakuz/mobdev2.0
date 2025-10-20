package ru.mirea.kuzmina.domain.repository;

import ru.mirea.kuzmina.domain.models.Dog;

public interface MLRepository {
    Dog identifyBreed(byte[] imageData);
}
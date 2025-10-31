package ru.mirea.kuzmina.domain.repository;

import ru.mirea.kuzmina.domain.models.Dog; // Используем Dog
import java.util.List;

public interface BreedsRepository {
    List<Dog> getBreeds() throws Exception;
}
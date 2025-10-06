package ru.mirea.kuzmina.dogcare.domain.repository;

import java.util.List;
import ru.mirea.kuzmina.dogcare.domain.models.Dog;

public interface BreedsRepository {
    List<Dog> getBreeds();
    List<Dog> searchBreeds(String query);
    Dog getBreedById(int id);
}
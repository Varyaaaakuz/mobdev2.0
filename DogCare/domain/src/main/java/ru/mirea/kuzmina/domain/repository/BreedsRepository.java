package ru.mirea.kuzmina.domain.repository;

import ru.mirea.kuzmina.domain.models.DogBreed;
import java.util.List;

public interface BreedsRepository {
    List<DogBreed> getBreeds();
    List<DogBreed> searchBreeds(String query);
    DogBreed getBreedById(String id);
}
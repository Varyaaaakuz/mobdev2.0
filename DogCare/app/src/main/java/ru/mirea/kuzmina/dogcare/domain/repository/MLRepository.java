package ru.mirea.kuzmina.dogcare.domain.repository;

import ru.mirea.kuzmina.dogcare.domain.models.Dog;

public interface MLRepository {
    Dog identifyBreed(byte[] imageData);
    String analyzeBreedCharacteristics(int breedId);
}
package ru.mirea.kuzmina.dogcare.domain.usecases;

import ru.mirea.kuzmina.dogcare.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.domain.repository.MLRepository;

public class IdentifyBreedUseCase {
    private final MLRepository mlRepository;

    public IdentifyBreedUseCase(MLRepository mlRepository) {
        this.mlRepository = mlRepository;
    }

    public Dog execute(byte[] imageData) {
        return mlRepository.identifyBreed(imageData);
    }
}
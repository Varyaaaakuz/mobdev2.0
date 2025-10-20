package ru.mirea.kuzmina.dogcare.domain.usecases;

import java.util.List;
import ru.mirea.kuzmina.dogcare.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.domain.repository.BreedsRepository;

public class GetBreedsUseCase {
    private final BreedsRepository breedsRepository;

    public GetBreedsUseCase(BreedsRepository breedsRepository) {
        this.breedsRepository = breedsRepository;
    }

    public List<Dog> execute() {
        return breedsRepository.getBreeds();
    }
}
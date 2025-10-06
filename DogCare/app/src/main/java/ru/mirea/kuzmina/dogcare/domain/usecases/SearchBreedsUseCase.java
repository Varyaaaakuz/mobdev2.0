package ru.mirea.kuzmina.dogcare.domain.usecases;

import java.util.List;
import ru.mirea.kuzmina.dogcare.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.domain.repository.BreedsRepository;

public class SearchBreedsUseCase {
    private final BreedsRepository breedsRepository;

    public SearchBreedsUseCase(BreedsRepository breedsRepository) {
        this.breedsRepository = breedsRepository;
    }

    public List<Dog> execute(String query) {
        return breedsRepository.searchBreeds(query);
    }
}
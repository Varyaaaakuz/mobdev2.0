package ru.mirea.kuzmina.data.repository;

import ru.mirea.kuzmina.domain.models.DogBreed;
import ru.mirea.kuzmina.domain.repository.BreedsRepository;
import java.util.ArrayList;
import java.util.List;

public class BreedsRepositoryImpl implements BreedsRepository {

    @Override
    public List<DogBreed> getBreeds() {
        List<DogBreed> breeds = new ArrayList<>();
        breeds.add(new DogBreed("1", "Золотистый ретривер",
                "Дружелюбная и умная порода, отличный компаньон для семьи",
                "Крупная", "Высокая активность", ""));
        breeds.add(new DogBreed("2", "Немецкая овчарка",
                "Верная и защитная порода, отличный рабочий пес",
                "Крупная", "Высокая активность", ""));
        breeds.add(new DogBreed("3", "Бигль",
                "Энергичная и дружелюбная порода, отличный охотник",
                "Средняя", "Средняя активность", ""));
        breeds.add(new DogBreed("4", "Пудель",
                "Умная и элегантная порода, отличный компаньон",
                "Средняя", "Высокая активность", ""));
        return breeds;
    }

    @Override
    public List<DogBreed> searchBreeds(String query) {
        List<DogBreed> allBreeds = getBreeds();
        List<DogBreed> result = new ArrayList<>();

        for (DogBreed breed : allBreeds) {
            if (breed.getName().toLowerCase().contains(query.toLowerCase()) ||
                    breed.getDescription().toLowerCase().contains(query.toLowerCase())) {
                result.add(breed);
            }
        }
        return result;
    }

    @Override
    public DogBreed getBreedById(String id) {
        List<DogBreed> allBreeds = getBreeds();
        for (DogBreed breed : allBreeds) {
            if (breed.getId().equals(id)) {
                return breed;
            }
        }
        return null;
    }
}
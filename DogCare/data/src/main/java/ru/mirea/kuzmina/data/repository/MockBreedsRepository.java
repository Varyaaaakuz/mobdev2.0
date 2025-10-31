package ru.mirea.kuzmina.data.repository;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.repository.BreedsRepository;

import java.util.ArrayList;
import java.util.List;

public class MockBreedsRepository implements BreedsRepository {

    @Override
    public List<Dog> getBreeds() throws Exception {
        List<Dog> mockDogs = new ArrayList<>();
        mockDogs.add(new Dog("1", "Золотистый ретривер",
                "Дружелюбная и умная порода, отличный компаньон для семьи",
                "", "Крупная", "Высокая активность"));

        mockDogs.add(new Dog("2", "Немецкая овчарка",
                "Верная и защитная порода, отличный рабочий пес",
                "", "Крупная", "Высокая активность"));

        mockDogs.add(new Dog("3", "Бигль",
                "Энергичная и дружелюбная порода, отличный охотник",
                "", "Средняя", "Средняя активность"));

        mockDogs.add(new Dog("4", "Пудель",
                "Умная и элегантная порода, отличный компаньон",
                "", "Средняя", "Высокая активность"));

        return mockDogs;
    }
}
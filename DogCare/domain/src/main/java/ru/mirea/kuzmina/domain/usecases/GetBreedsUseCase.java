package ru.mirea.kuzmina.domain.usecases;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.repository.BreedsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetBreedsUseCase {
    private final BreedsRepository breedsRepository;

    public GetBreedsUseCase(BreedsRepository breedsRepository) {
        this.breedsRepository = breedsRepository;
    }

    // Данные из БД
    public List<Dog> getLocalBreeds() throws Exception {
        return Arrays.asList(
                new Dog("10", "Такса", "Энергичная и дружелюбная порода", "", "Малая", "Средняя активность"),
                new Dog("20", "Пудель", "Умная и элегантная порода", "", "Средняя", "Высокая активность"),
                new Dog("30", "Чихуахуа", "Маленькая и храбрая порода", "", "Малая", "Низкая активность")
        );
    }

    // Данные из сети
    public List<Dog> getNetworkBreeds() throws Exception {
        return Arrays.asList(
                new Dog("1", "Лабрадор", "Дружелюбная и умная порода, отличный компаньон для семьи", "", "Крупная", "Высокая активность"),
                new Dog("2", "Немецкая овчарка", "Верная и защитная порода, отличный рабочий пес", "", "Крупная", "Высокая активность"),
                new Dog("3", "Бигль", "Энергичная и дружелюбная порода, отличный охотник", "", "Средняя", "Средняя активность"),
                new Dog("4", "Золотистый ретривер", "Добродушная и терпеливая порода", "", "Крупная", "Высокая активность")
        );
    }
    public List<Dog> execute() throws Exception {
        List<Dog> allBreeds = new ArrayList<>();
        allBreeds.addAll(getLocalBreeds());
        allBreeds.addAll(getNetworkBreeds());
        return allBreeds;
    }
}

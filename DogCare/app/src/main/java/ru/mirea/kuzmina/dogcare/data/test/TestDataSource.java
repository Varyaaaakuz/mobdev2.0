package ru.mirea.kuzmina.dogcare.data.test;

import java.util.Arrays;
import java.util.List;
import ru.mirea.kuzmina.dogcare.domain.models.Dog;

public class TestDataSource {

    public List<Dog> getTestBreeds() {
        return Arrays.asList(
                new Dog(1, "Лабрадор", "Дружелюбная семейная собака", "labrador.jpg",
                        "Регулярные прогулки и сбалансированное питание"),
                new Dog(2, "Овчарка", "Умная и преданная", "shepherd.jpg",
                        "Активные тренировки и умственная нагрузка"),
                new Dog(3, "Такса", "Энергичная и любопытная", "dachshund.jpg",
                        "Контроль веса и забота о позвоночнике")
        );
    }

    public String getTestCareAdvice(int breedId) {
        switch(breedId) {
            case 1: return "Лабрадоры нуждаются в регулярных физических нагрузках";
            case 2: return "Овчаркам важна социализация и тренировки";
            case 3: return "Таксам противопоказаны прыжки с высоты";
            default: return "Общие рекомендации по уходу за собакой";
        }
    }
}
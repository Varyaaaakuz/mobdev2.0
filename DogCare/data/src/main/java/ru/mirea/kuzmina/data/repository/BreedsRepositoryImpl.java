package ru.mirea.kuzmina.data.repository;

import android.util.Log;

import ru.mirea.kuzmina.data.network.NetworkApi;
import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.repository.BreedsRepository;

import java.util.ArrayList;
import java.util.List;

public class BreedsRepositoryImpl implements BreedsRepository {

    private static final String TAG = "BreedsRepositoryImpl";
    private final NetworkApi networkApi;

    public BreedsRepositoryImpl() {
        this.networkApi = NetworkApi.Creator.create();
    }

    @Override
    public List<Dog> getBreeds() throws Exception {
        try {
            var response = networkApi.getRandomDogImages().execute();

            if (response.isSuccessful() && response.body() != null) {
                List<String> imageUrls = response.body().message;
                return mapToDomainModelsWithRussianNames(imageUrls);
            } else {
                Log.e(TAG, "Network error: " + response.code());
                return getLocalRussianBreeds();
            }

        } catch (Exception e) {
            Log.e(TAG, "Error: " + e.getMessage());
            return getLocalRussianBreeds();
        }
    }

    private List<Dog> mapToDomainModelsWithRussianNames(List<String> imageUrls) {
        List<Dog> dogs = new ArrayList<>();
        String[] breedNames = {
                "Лабрадор ретривер",
                "Немецкая овчарка",
                "Золотистый ретривер",
                "Французский бульдог",
                "Бигль",
                "Пудель",
                "Сибирский хаски"
        };

        String[] descriptions = {
                "Дружелюбная и активная семейная собака, отличный компаньон для детей",
                "Умная, преданная и универсальная рабочая порода с защитными инстинктами",
                "Добродушная, терпеливая и очень умная порода, идеальная для семьи",
                "Компактная, дружелюбная собака с характерной внешностью и веселым нравом",
                "Энергичная охотничья порода с острым нюхом и дружелюбным характером",
                "Очень умная и элегантная порода, известная своей гипоаллергенной шерстью",
                "Выносливая ездовая собака с дружелюбным характером и густой шерстью"
        };

        String[] sizes = {"Крупная", "Крупная", "Крупная", "Малая", "Средняя", "Средняя", "Крупная"};
        String[] activityLevels = {"Высокая", "Высокая", "Средняя", "Низкая", "Высокая", "Высокая", "Очень высокая"};

        for (int i = 0; i < Math.min(7, imageUrls.size()); i++) {
            dogs.add(new Dog(
                    String.valueOf(i + 1),
                    breedNames[i],
                    descriptions[i],
                    imageUrls.get(i),
                    sizes[i],
                    activityLevels[i]
            ));
        }

        return dogs;
    }

    private List<Dog> getLocalRussianBreeds() {
        List<Dog> dogs = new ArrayList<>();
        // Локальные данные на случай ошибки сети
        dogs.add(new Dog("1", "Лабрадор ретривер",
                "Дружелюбная и активная семейная собака, отличный компаньон для детей",
                "", "Крупная", "Высокая"));
        dogs.add(new Dog("2", "Немецкая овчарка",
                "Умная, преданная и универсальная рабочая порода с защитными инстинктами",
                "", "Крупная", "Высокая"));
        dogs.add(new Dog("3", "Золотистый ретривер",
                "Добродушная, терпеливая и очень умная порода, идеальная для семьи",
                "", "Крупная", "Средняя"));
        dogs.add(new Dog("4", "Французский бульдог",
                "Компактная, дружелюбная собака с характерной внешностью и веселым нравом",
                "", "Малая", "Низкая"));
        dogs.add(new Dog("5", "Бигль",
                "Энергичная охотничья порода с острым нюхом и дружелюбным характером",
                "", "Средняя", "Высокая"));
        dogs.add(new Dog("6", "Пудель",
                "Очень умная и элегантная порода, известная своей гипоаллергенной шерстью",
                "", "Средняя", "Высокая"));
        dogs.add(new Dog("7", "Сибирский хаски",
                "Выносливая ездовая собака с дружелюбным характером и густой шерстью",
                "", "Крупная", "Очень высокая"));

        return dogs;
    }
}
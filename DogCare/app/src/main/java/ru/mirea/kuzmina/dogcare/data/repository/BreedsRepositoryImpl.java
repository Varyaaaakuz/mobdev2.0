package ru.mirea.kuzmina.dogcare.data.repository;

import java.util.ArrayList;
import java.util.List;
import ru.mirea.kuzmina.dogcare.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.domain.repository.BreedsRepository;
import ru.mirea.kuzmina.dogcare.data.test.TestDataSource;

public class BreedsRepositoryImpl implements BreedsRepository {
    private TestDataSource testDataSource;

    public BreedsRepositoryImpl() {
        this.testDataSource = new TestDataSource();
    }

    @Override
    public List<Dog> getBreeds() {
        return testDataSource.getTestBreeds();
    }

    @Override
    public List<Dog> searchBreeds(String query) {
        List<Dog> allBreeds = testDataSource.getTestBreeds();
        List<Dog> result = new ArrayList<>();
        for (Dog dog : allBreeds) {
            if (dog.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(dog);
            }
        }
        return result;
    }

    @Override
    public Dog getBreedById(int id) {
        for (Dog dog : testDataSource.getTestBreeds()) {
            if (dog.getId() == id) {
                return dog;
            }
        }
        return null;
    }
}
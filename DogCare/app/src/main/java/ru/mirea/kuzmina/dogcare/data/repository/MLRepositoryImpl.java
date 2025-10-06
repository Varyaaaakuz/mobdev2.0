package ru.mirea.kuzmina.dogcare.data.repository;

import ru.mirea.kuzmina.dogcare.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.domain.repository.MLRepository;
import ru.mirea.kuzmina.dogcare.data.test.TestDataSource;

public class MLRepositoryImpl implements MLRepository {
    private TestDataSource testDataSource;

    public MLRepositoryImpl() {
        this.testDataSource = new TestDataSource();
    }

    @Override
    public Dog identifyBreed(byte[] imageData) {
        // Тестовая реализация - возвращает первую породу из тестовых данных
        return testDataSource.getTestBreeds().get(0);
    }

    @Override
    public String analyzeBreedCharacteristics(int breedId) {
        return "Тестовый анализ характеристик породы ID: " + breedId;
    }
}
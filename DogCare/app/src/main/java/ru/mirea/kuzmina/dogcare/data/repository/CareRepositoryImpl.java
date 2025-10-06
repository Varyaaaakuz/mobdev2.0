package ru.mirea.kuzmina.dogcare.data.repository;

import ru.mirea.kuzmina.dogcare.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.domain.repository.CareRepository;
import ru.mirea.kuzmina.dogcare.data.test.TestDataSource;

public class CareRepositoryImpl implements CareRepository {
    private TestDataSource testDataSource;

    public CareRepositoryImpl() {
        this.testDataSource = new TestDataSource();
    }

    @Override
    public String getCareAdvice(int breedId) {
        return testDataSource.getTestCareAdvice(breedId);
    }

    @Override
    public boolean saveCareHistory(Dog dog) {
        // Тестовая реализация - всегда успешно
        return true;
    }
}
package ru.mirea.kuzmina.dogcare.domain.repository;

import ru.mirea.kuzmina.dogcare.domain.models.Dog;

public interface CareRepository {
    String getCareAdvice(int breedId);
    boolean saveCareHistory(Dog dog);
}
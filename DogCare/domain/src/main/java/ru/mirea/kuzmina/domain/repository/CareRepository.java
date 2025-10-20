package ru.mirea.kuzmina.domain.repository;

public interface CareRepository {
    String getCareAdvice(String breed);
    void saveCarePreference(String breed, String preference);
}
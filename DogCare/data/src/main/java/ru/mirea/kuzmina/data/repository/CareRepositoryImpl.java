package ru.mirea.kuzmina.data.repository;

import ru.mirea.kuzmina.domain.repository.CareRepository;

public class CareRepositoryImpl implements CareRepository {

    @Override
    public String getCareAdvice(String breed) {
        switch (breed.toLowerCase()) {
            case "labrador retriever":
                return "Labradors need daily exercise and regular grooming.";
            case "german shepherd dog":
                return "German Shepherds need mental stimulation and regular training.";
            case "golden retriever":
                return "Golden Retrievers need regular brushing and plenty of exercise.";
            default:
                return "General dog care: regular vet checkups, balanced diet, and daily exercise.";
        }
    }

    @Override
    public void saveCarePreference(String breed, String preference) {
        System.out.println("Saved preference for " + breed + ": " + preference);
    }
}
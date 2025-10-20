package ru.mirea.kuzmina.dogcare.domain.usecases;

import ru.mirea.kuzmina.dogcare.domain.repository.CareRepository;

public class GetCareAdviceUseCase {
    private final CareRepository careRepository;

    public GetCareAdviceUseCase(CareRepository careRepository) {
        this.careRepository = careRepository;
    }

    public String execute(int breedId) {
        return careRepository.getCareAdvice(breedId);
    }
}
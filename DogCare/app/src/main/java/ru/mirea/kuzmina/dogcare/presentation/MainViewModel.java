package ru.mirea.kuzmina.dogcare.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.repository.BreedsRepository;
import ru.mirea.kuzmina.data.repository.BreedsRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Dog>> breeds = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private final BreedsRepository breedsRepository;
    private final ExecutorService executorService;

    private List<Dog> allBreeds = new ArrayList<>();

    public MainViewModel() {
        this.breedsRepository = new BreedsRepositoryImpl();
        this.executorService = Executors.newSingleThreadExecutor();
        this.isLoading.setValue(false);
        loadBreedsFromNetwork();
    }

    public LiveData<List<Dog>> getBreeds() {
        return breeds;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loadBreedsFromNetwork() {
        isLoading.setValue(true);
        errorMessage.setValue(null);

        executorService.execute(() -> {
            try {
                List<Dog> loadedBreeds = breedsRepository.getBreeds();
                allBreeds = new ArrayList<>(loadedBreeds);

                breeds.postValue(allBreeds);
                isLoading.postValue(false);

                Log.d("MainViewModel", "Successfully loaded " + loadedBreeds.size() + " breeds");

            } catch (Exception e) {
                Log.e("MainViewModel", "Error loading breeds: " + e.getMessage());
                errorMessage.postValue("Ошибка загрузки: " + e.getMessage());
                isLoading.postValue(false);

                breeds.postValue(createFallbackBreeds());
            }
        });
    }

    public void addBreed(Dog breed) {
        List<Dog> currentList = breeds.getValue();
        if (currentList != null) {
            currentList.add(breed);
            breeds.setValue(currentList);
        }
    }

    public void searchBreeds(String query) {
        if (query == null || query.trim().isEmpty()) {
            breeds.setValue(allBreeds);
        } else {
            List<Dog> filtered = new ArrayList<>();
            for (Dog dog : allBreeds) {
                if (dog.getName().toLowerCase().contains(query.toLowerCase())) {
                    filtered.add(dog);
                }
            }
            breeds.setValue(filtered);
        }
    }

    public void retryLoading() {
        loadBreedsFromNetwork();
    }
    private List<Dog> createFallbackBreeds() {
        List<Dog> fallbackList = new ArrayList<>();
        fallbackList.add(new Dog("1", "Лабрадор", "Дружелюбная семейная собака",
                "https://images.dog.ceo/breeds/labrador/n02099712_100.jpg", "Крупная", "Высокая"));
        fallbackList.add(new Dog("2", "Немецкая овчарка", "Умная и преданная",
                "https://images.dog.ceo/breeds/german-shepherd/n02106662_100.jpg", "Крупная", "Высокая"));
        fallbackList.add(new Dog("3", "Такса", "Энергичная и смелая",
                "https://images.dog.ceo/breeds/dachshund/dachshund-1.jpg", "Малая", "Средняя"));
        return fallbackList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }
}
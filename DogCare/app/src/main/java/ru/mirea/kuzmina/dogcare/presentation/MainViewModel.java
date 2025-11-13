package ru.mirea.kuzmina.dogcare.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.kuzmina.domain.models.Dog;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Dog>> breeds = new MutableLiveData<>();
    private final List<Dog> initialBreeds = createInitialBreeds();
    public MainViewModel() {
        breeds.setValue(initialBreeds);
    }
    public LiveData<List<Dog>> getBreeds() {
        return breeds;
    }
    public void addBreed(Dog breed) {
        List<Dog> currentList = breeds.getValue();
        if (currentList != null) {
            currentList.add(breed);
            breeds.setValue(currentList);
        }
    }
    public void searchBreeds(String query) {
        if (query == null || query.trim().length() == 0) {
            breeds.setValue(initialBreeds);
        } else {
            List<Dog> filtered = new ArrayList<>();
            for (Dog dog : initialBreeds) {
                if (dog.getName().toLowerCase().contains(query.toLowerCase())) {
                    filtered.add(dog);
                }
            }
            breeds.setValue(filtered);
        }
    }
    private List<Dog> createInitialBreeds() {
        List<Dog> breedList = new ArrayList<>();
        breedList.add(new Dog("1", "Лабрадор", "Дружелюбная семейная собака", "labrador_image", "Крупная", "Высокая"));
        breedList.add(new Dog("2", "Немецкая овчарка", "Умная и преданная", "german_shepherd_image", "Крупная", "Высокая"));
        breedList.add(new Dog("3", "Такса", "Энергичная и смелая", "dachshund_image", "Малая", "Средняя"));
        breedList.add(new Dog("4", "Пудель", "Умная и элегантная", "poodle_image", "Средняя", "Высокая"));
        breedList.add(new Dog("5", "Бигль", "Дружелюбная охотничья", "beagle_image", "Средняя", "Средняя"));
        return breedList;
    }
}
package ru.mirea.kuzmina.dogcare.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.usecases.GetBreedsUseCase;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final GetBreedsUseCase getBreedsUseCase;
    private final MutableLiveData<List<Dog>> _localBreeds = new MutableLiveData<>();
    private final MutableLiveData<List<Dog>> _networkBreeds = new MutableLiveData<>();

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    private final MediatorLiveData<List<Dog>> _combinedBreeds = new MediatorLiveData<>();
    public LiveData<List<Dog>> combinedBreeds = _combinedBreeds;

    public MainViewModel(GetBreedsUseCase getBreedsUseCase) {
        this.getBreedsUseCase = getBreedsUseCase;
        setupMediatorLiveData();
        loadBreeds();
    }

    private void setupMediatorLiveData() {
        _combinedBreeds.addSource(_localBreeds, localData -> {
            combineData(localData, _networkBreeds.getValue());
        });

        _combinedBreeds.addSource(_networkBreeds, networkData -> {
            combineData(_localBreeds.getValue(), networkData);
        });
    }

    private void combineData(List<Dog> localData, List<Dog> networkData) {
        List<Dog> allBreeds = new ArrayList<>();
        if (localData != null) {
            allBreeds.addAll(localData);
        }
        if (networkData != null) {
            for (Dog networkDog : networkData) {
                boolean found = false;
                for (int i = 0; i < allBreeds.size(); i++) {
                    if (allBreeds.get(i).getId().equals(networkDog.getId())) {
                        allBreeds.set(i, networkDog);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    allBreeds.add(networkDog);
                }
            }
        }

        if (!allBreeds.isEmpty()) {
            _combinedBreeds.setValue(allBreeds);
        }
    }

    public void loadBreeds() {
        _isLoading.setValue(true);
        // БД
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                List<Dog> localBreeds = getBreedsUseCase.getLocalBreeds();
                _localBreeds.postValue(localBreeds);
            } catch (Exception e) {
                Log.e("MainViewModel", "Ошибка БД", e);
            }
        }).start();
        // Сеть
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                List<Dog> networkBreeds = getBreedsUseCase.getNetworkBreeds();
                _networkBreeds.postValue(networkBreeds);
                _isLoading.postValue(false);
            } catch (Exception e) {
                Log.e("MainViewModel", "Ошибка сети", e);
                _isLoading.postValue(false);
            }
        }).start();
    }

    public void searchBreeds(String query) {
        try {
            List<Dog> currentBreeds = _combinedBreeds.getValue();
            if (currentBreeds == null) return;

            if (query.isEmpty()) {
                // Перезагружаем данные
                loadBreeds();
            } else {
                List<Dog> filtered = new ArrayList<>();
                for (Dog dog : currentBreeds) {
                    if (dog.getName().toLowerCase().contains(query.toLowerCase())) {
                        filtered.add(dog);
                    }
                }
                _combinedBreeds.setValue(filtered);
            }
        } catch (Exception e) {
            Log.e("MainViewModel", "Ошибка поиска", e);
        }
    }

    public void refreshData() {
        loadBreeds();
    }
}
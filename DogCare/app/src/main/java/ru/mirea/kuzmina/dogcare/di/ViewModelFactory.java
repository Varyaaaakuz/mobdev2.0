package ru.mirea.kuzmina.dogcare.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;

import ru.mirea.kuzmina.data.repository.BreedsRepositoryImpl;
import ru.mirea.kuzmina.domain.usecases.GetBreedsUseCase;
import ru.mirea.kuzmina.dogcare.presentation.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //зависимости
            BreedsRepositoryImpl breedsRepository = new BreedsRepositoryImpl();
            GetBreedsUseCase getBreedsUseCase = new GetBreedsUseCase(breedsRepository);

            return (T) new MainViewModel(getBreedsUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
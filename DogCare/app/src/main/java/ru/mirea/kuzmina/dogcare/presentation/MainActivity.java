package ru.mirea.kuzmina.dogcare.presentation;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.data.repository.*;
import ru.mirea.kuzmina.dogcare.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.domain.usecases.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DogCareApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testAllUseCases();
    }

    private void testAllUseCases() {
        // авторизация
        AuthRepositoryImpl authRepo = new AuthRepositoryImpl(this);
        LoginUseCase loginUseCase = new LoginUseCase(authRepo);
        boolean loginResult = loginUseCase.execute("test@mail.com", "password");
        Log.d(TAG, "Login result: " + loginResult);
        // получение пород
        BreedsRepositoryImpl breedsRepo = new BreedsRepositoryImpl();
        GetBreedsUseCase getBreedsUseCase = new GetBreedsUseCase(breedsRepo);
        List<Dog> breeds = getBreedsUseCase.execute();
        for (Dog dog : breeds) {
            Log.d(TAG, "Breed: " + dog.getName() + " - " + dog.getDescription());
        }
        // поиск
        SearchBreedsUseCase searchUseCase = new SearchBreedsUseCase(breedsRepo);
        List<Dog> searchResults = searchUseCase.execute("лабрадор");
        Log.d(TAG, "Search found: " + searchResults.size() + " results");
        //  определение породы
        MLRepositoryImpl mlRepo = new MLRepositoryImpl();
        IdentifyBreedUseCase identifyUseCase = new IdentifyBreedUseCase(mlRepo);
        Dog identifiedBreed = identifyUseCase.execute(new byte[]{1, 2, 3}); // тестовые байты
        Log.d(TAG, "Identified breed: " + identifiedBreed.getName());
        // советы по уходу
        CareRepositoryImpl careRepo = new CareRepositoryImpl();
        GetCareAdviceUseCase careUseCase = new GetCareAdviceUseCase(careRepo);
        String advice = careUseCase.execute(1);
        Log.d(TAG, "Care advice: " + advice);
    }
}
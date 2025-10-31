package ru.mirea.kuzmina.dogcare.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.di.ViewModelFactory;
import ru.mirea.kuzmina.dogcare.presentation.adapters.BreedsAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView breedsRecyclerView;
    private EditText searchEditText;
    private BreedsAdapter breedsAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupViewModel();
        setupObservers();
        setupRecyclerView();
    }
    private void initViews() {
        breedsRecyclerView = findViewById(R.id.breedsRecyclerView);
        searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mainViewModel.searchBreeds(s.toString());
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        Button refreshButton = findViewById(R.id.refreshButton);
        if (refreshButton != null) {
            refreshButton.setOnClickListener(v -> mainViewModel.refreshData());
        }
    }

    private void setupViewModel() {
        ViewModelFactory factory = new ViewModelFactory(this);
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    private void setupObservers() {
        mainViewModel.combinedBreeds.observe(this, dogs -> {
            if (dogs != null) {
                breedsAdapter.updateBreeds(dogs);
                if (!dogs.isEmpty()) {
                    boolean hasLocal = dogs.stream().anyMatch(d -> d.getName().contains("Такса"));
                    boolean hasNetwork = dogs.stream().anyMatch(d -> d.getName().contains("Лабрадор"));

                    if (hasLocal && hasNetwork) {
                        Toast.makeText(this, "Все данные загружены", Toast.LENGTH_SHORT).show();
                    } else if (hasLocal) {
                        Toast.makeText(this, "Данные из кэша", Toast.LENGTH_SHORT).show();
                    } else if (hasNetwork) {
                        Toast.makeText(this, "Данные из сети", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setupRecyclerView() {
        breedsAdapter = new BreedsAdapter(new ArrayList<>(), breed -> {
            Toast.makeText(this, "Выбрана: " + breed.getName(), Toast.LENGTH_SHORT).show();
        });

        breedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        breedsRecyclerView.setAdapter(breedsAdapter);
    }
}
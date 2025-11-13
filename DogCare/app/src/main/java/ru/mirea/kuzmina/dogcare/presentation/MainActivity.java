package ru.mirea.kuzmina.dogcare.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

import ru.mirea.kuzmina.dogcare.R;
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
    }

    private void setupViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void setupObservers() {
        mainViewModel.getBreeds().observe(this, dogs -> {
            if (dogs != null) {
                breedsAdapter.updateBreeds(dogs);
            }
        });
    }

    private void setupRecyclerView() {
        breedsAdapter = new BreedsAdapter(new ArrayList<>(), breed -> {
        });

        breedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        breedsRecyclerView.setAdapter(breedsAdapter);
    }
}
package ru.mirea.kuzmina.dogcare.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.presentation.adapters.BreedsAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView breedsRecyclerView;
    private EditText searchEditText;
    private ProgressBar loadingProgressBar;
    private TextView errorTextView;
    private TextView emptyTextView;

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
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        errorTextView = findViewById(R.id.errorTextView);
        emptyTextView = findViewById(R.id.emptyTextView);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mainViewModel.searchBreeds(s.toString());
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        errorTextView.setOnClickListener(v -> mainViewModel.retryLoading());
    }

    private void setupViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void setupObservers() {
        mainViewModel.getBreeds().observe(this, dogs -> {
            if (dogs != null) {
                breedsAdapter.updateBreeds(dogs);
                if (dogs.isEmpty()) {
                    emptyTextView.setVisibility(View.VISIBLE);
                    breedsRecyclerView.setVisibility(View.GONE);
                } else {
                    emptyTextView.setVisibility(View.GONE);
                    breedsRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        mainViewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                errorTextView.setText(error);
                errorTextView.setVisibility(View.VISIBLE);
                breedsRecyclerView.setVisibility(View.GONE);
                emptyTextView.setVisibility(View.GONE);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            } else {
                errorTextView.setVisibility(View.GONE);
            }
        });

        mainViewModel.getIsLoading().observe(this, isLoading -> {
            loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                breedsRecyclerView.setVisibility(View.GONE);
                errorTextView.setVisibility(View.GONE);
                emptyTextView.setVisibility(View.GONE);
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
package ru.mirea.kuzmina.dogcare.presentation;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.kuzmina.data.repository.BreedsRepositoryImpl;
import ru.mirea.kuzmina.domain.models.DogBreed;
import ru.mirea.kuzmina.domain.repository.BreedsRepository;
import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.presentation.adapters.BreedsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BreedsAdapter.OnBreedClickListener {

    private RecyclerView breedsRecyclerView;
    private SearchView searchView;
    private BreedsAdapter breedsAdapter;
    private BreedsRepository breedsRepository;
    private List<DogBreed> allBreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRepository();
        setupRecyclerView();
        setupSearchView();
    }

    private void initViews() {
        breedsRecyclerView = findViewById(R.id.breedsRecyclerView);
        searchView = findViewById(R.id.searchView);
    }

    private void setupRepository() {
        breedsRepository = new BreedsRepositoryImpl();
        allBreeds = breedsRepository.getBreeds();
    }

    private void setupRecyclerView() {
        breedsAdapter = new BreedsAdapter(allBreeds, this);
        breedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        breedsRecyclerView.setAdapter(breedsAdapter);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    breedsAdapter.updateBreeds(allBreeds);
                } else {
                    List<DogBreed> filteredBreeds = breedsRepository.searchBreeds(newText);
                    breedsAdapter.updateBreeds(filteredBreeds);
                }
                return true;
            }
        });
    }

    @Override
    public void onBreedClick(DogBreed breed) {

    }
}
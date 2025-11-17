package ru.mirea.kuzmina.dogcare.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.presentation.fragments.CareAdviceFragment;
import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.domain.models.User;
import ru.mirea.kuzmina.dogcare.presentation.adapters.BreedsAdapter;
import ru.mirea.kuzmina.dogcare.presentation.fragments.BreedDetailsFragment;
import ru.mirea.kuzmina.dogcare.presentation.fragments.ProfileFragment;

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
        checkAuthentication();

        setupObservers();
        setupRecyclerView();
    }

    private void checkAuthentication() {
        User userFromIntent = getUserFromIntent();
        if (userFromIntent != null) {
            mainViewModel.setCurrentUser(userFromIntent);
            return;
        }

        User currentUser = mainViewModel.getCurrentUser().getValue();
        if (currentUser == null) {
            goToLoginActivity();
        }
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, ru.mirea.kuzmina.dogcare.presentation.auth.LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private User getUserFromIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user_id")) {
            String userId = intent.getStringExtra("user_id");
            String userName = intent.getStringExtra("user_name");
            String userEmail = intent.getStringExtra("user_email");

            if (userId != null && userName != null && userEmail != null) {
                return new User(userId, userName, userEmail);
            }
        }
        return null;
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

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        errorTextView.setOnClickListener(v -> mainViewModel.retryLoading());

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_catalog) {
                showBreedsList();
                return true;
            } else if (itemId == R.id.navigation_search) {
                searchEditText.requestFocus();
                return true;
            } else if (itemId == R.id.navigation_identify) {
                Toast.makeText(this, "Функция определения породы", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                navigateToProfile();
                return true;
            }
            return false;
        });
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
        mainViewModel.getSelectedBreed().observe(this, breed -> {
            if (breed != null) {
                navigateToBreedDetails(breed);
            }
        });

        mainViewModel.getCurrentUser().observe(this, user -> {
            if (user == null) {
                goToLoginActivity();
            }
        });
    }

    private void setupRecyclerView() {
        breedsAdapter = new BreedsAdapter(new ArrayList<>(), breed -> {
            mainViewModel.selectBreed(breed);
        });

        breedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        breedsRecyclerView.setAdapter(breedsAdapter);
    }
    public void navigateToBreedDetails(Dog breed) {
        findViewById(R.id.main_content).setVisibility(View.GONE);
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);

        BreedDetailsFragment fragment = new BreedDetailsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("breed_details")
                .commit();
    }
    public void navigateToCareAdvice() {
        CareAdviceFragment fragment = new CareAdviceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("care_advice")
                .commit();
    }
    public void navigateToProfile() {
        findViewById(R.id.main_content).setVisibility(View.GONE);
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);

        ProfileFragment fragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("profile")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();

            showBreedsList();
        } else {
            super.onBackPressed();
        }
    }

    private void showBreedsList() {
        findViewById(R.id.fragment_container).setVisibility(View.GONE);
        findViewById(R.id.main_content).setVisibility(View.VISIBLE);
        mainViewModel.clearSelectedBreed();
    }
}
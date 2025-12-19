package ru.mirea.kuzmina.dogcare.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.domain.models.User;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        setupViewModel();
        checkAuthentication();
        setupNavigation();
    }

    private void setupViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void checkAuthentication() {
        User userFromIntent = getUserFromIntent();
        if (userFromIntent != null) {
            mainViewModel.setCurrentUser(userFromIntent);
            return;
        }

        mainViewModel.getCurrentUser().observe(this, user -> {
            if (user == null) {
                goToLoginActivity();
            }
        });
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

    private void setupNavigation() {
        try {
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment);

            if (navHostFragment != null) {
                navController = navHostFragment.getNavController();

                BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

                NavigationUI.setupWithNavController(bottomNav, navController);

                Log.d("Navigation", "Navigation setup successful");
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error setting up navigation", e);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
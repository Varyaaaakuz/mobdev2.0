package ru.mirea.kuzmina.dogcare.presentation.fragments;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.kuzmina.data.local.AppDatabase;
import ru.mirea.kuzmina.data.repository.AuthRepositoryImpl;
import ru.mirea.kuzmina.data.storage.UserPreferences;
import ru.mirea.kuzmina.domain.models.User;
import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.presentation.MainViewModel;

public class ProfileFragment extends Fragment {

    private MainViewModel viewModel;
    private TextView tvUserName, tvUserEmail, tvFavoriteCount, tvTotalCount;
    private Button btnLogout, btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initViews(view);
        setupObservers();
    }

    private void initViews(View view) {
        tvUserName = view.findViewById(R.id.tv_user_name);
        tvUserEmail = view.findViewById(R.id.tv_user_email);
        tvFavoriteCount = view.findViewById(R.id.tv_favorite_count);
        tvTotalCount = view.findViewById(R.id.tv_total_count);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnBack = view.findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        btnLogout.setOnClickListener(v -> performLogout());

        tvFavoriteCount.setText("0");
        tvTotalCount.setText("6");
    }

    private void setupObservers() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                displayUserInfo(user);
            } else {
                requireActivity().onBackPressed();
            }
        });
    }

    private void displayUserInfo(User user) {
        tvUserName.setText(user.getName());
        tvUserEmail.setText(user.getEmail());
    }

    private void performLogout() {
        btnLogout.setEnabled(false);
        btnLogout.setText("Выход...");

        new Thread(() -> {
            try {
                AuthRepositoryImpl authRepository = new AuthRepositoryImpl(
                        FirebaseAuth.getInstance(),
                        new UserPreferences(requireContext()),
                        AppDatabase.getInstance(requireContext())
                );

                authRepository.signOut();

                requireActivity().runOnUiThread(() -> {
                    viewModel.logout();
                    btnLogout.setEnabled(true);
                    btnLogout.setText("Выйти из аккаунта");
                });

            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(requireContext(), "Ошибка выхода: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    btnLogout.setEnabled(true);
                    btnLogout.setText("Выйти из аккаунта");
                });
            }
        }).start();
    }
}
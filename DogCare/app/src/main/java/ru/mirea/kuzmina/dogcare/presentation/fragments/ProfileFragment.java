package ru.mirea.kuzmina.dogcare.presentation.fragments;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.kuzmina.data.local.AppDatabase;
import ru.mirea.kuzmina.data.repository.AuthRepositoryImpl;
import ru.mirea.kuzmina.data.storage.UserPreferences;
import ru.mirea.kuzmina.domain.models.User;
import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.databinding.FragmentProfileBinding;
import ru.mirea.kuzmina.dogcare.presentation.MainViewModel;

public class ProfileFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentProfileBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        initViews();
        setupObservers();
    }

    private void initViews() {
        binding.btnBack.setOnClickListener(v -> {
            navController.navigateUp();
        });

        binding.btnLogout.setOnClickListener(v -> performLogout());

        binding.tvFavoriteCount.setText("0");
        binding.tvTotalCount.setText("6");
    }

    private void setupObservers() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                displayUserInfo(user);
            } else {
                navController.navigateUp();
            }
        });
    }

    private void displayUserInfo(User user) {
        binding.tvUserName.setText(user.getName());
        binding.tvUserEmail.setText(user.getEmail());
    }

    private void performLogout() {
        binding.btnLogout.setEnabled(false);
        binding.btnLogout.setText("Выход...");

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
                    binding.btnLogout.setEnabled(true);
                    binding.btnLogout.setText("Выйти из аккаунта");
                });

            } catch (Exception e) {
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(requireContext(), "Ошибка выхода: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    binding.btnLogout.setEnabled(true);
                    binding.btnLogout.setText("Выйти из аккаунта");
                });
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package ru.mirea.kuzmina.dogcare.presentation.fragments;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.databinding.FragmentBreedDetailsBinding;
import ru.mirea.kuzmina.dogcare.presentation.MainViewModel;

public class BreedDetailsFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentBreedDetailsBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentBreedDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        setupObservers();
        setupClickListeners();
    }

    private void setupObservers() {
        viewModel.getSelectedBreed().observe(getViewLifecycleOwner(), breed -> {
            if (breed != null) {
                displayBreedDetails(breed);
            }
        });
    }

    private void setupClickListeners() {
        binding.btnBack.setOnClickListener(v -> {
            navController.navigateUp();
        });

        binding.btnCareAdvice.setOnClickListener(v -> navigateToCareAdvice());
    }

    private void displayBreedDetails(Dog breed) {
        binding.tvBreedName.setText(breed.getName());
        binding.tvBreedDescription.setText(breed.getDescription());

        if (breed.getImageUrl() != null && !breed.getImageUrl().isEmpty()) {
            Glide.with(this)
                    .load(breed.getImageUrl())
                    .placeholder(R.drawable.rounded_corners)
                    .into(binding.ivBreedImage);
        }
        binding.layoutCharacteristics.removeAllViews();

        addCharacteristic("Размер", breed.getSize() != null ? breed.getSize() : "Средний");
        addCharacteristic("Активность", breed.getActivityLevel() != null ? breed.getActivityLevel() : "Высокая");
        addCharacteristic("Подходит для семьи", "Да");
        addCharacteristic("Дрессировка", "Легкая");
        addCharacteristic("Линька", "Умеренная");
        addCharacteristic("Охранные качества", "Средние");
    }

    private void addCharacteristic(String title, String value) {
        View characteristicView = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_characteristic, binding.layoutCharacteristics, false);

        TextView tvTitle = characteristicView.findViewById(R.id.tv_title);
        TextView tvValue = characteristicView.findViewById(R.id.tv_value);

        tvTitle.setText(title);
        tvValue.setText(value);

        binding.layoutCharacteristics.addView(characteristicView);
    }

    private void navigateToCareAdvice() {
        navController.navigate(R.id.action_breedDetails_to_careAdvice);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
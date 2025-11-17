package ru.mirea.kuzmina.dogcare.presentation.fragments;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.presentation.MainViewModel;

public class BreedDetailsFragment extends Fragment {

    private MainViewModel viewModel;
    private TextView tvBreedName, tvBreedDescription, tvCareAdvice;
    private ImageView ivBreedImage;
    private LinearLayout layoutCharacteristics;
    private Button btnBack;
    private Button btnCareAdvice;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_breed_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initViews(view);
        setupObservers();
        setupClickListeners();
    }

    private void initViews(View view) {
        tvBreedName = view.findViewById(R.id.tv_breed_name);
        tvBreedDescription = view.findViewById(R.id.tv_breed_description);
        tvCareAdvice = view.findViewById(R.id.tv_care_advice);
        ivBreedImage = view.findViewById(R.id.iv_breed_image);
        layoutCharacteristics = view.findViewById(R.id.layout_characteristics);
        btnBack = view.findViewById(R.id.btn_back);
        btnCareAdvice = view.findViewById(R.id.btn_care_advice);
    }

    private void setupObservers() {
        viewModel.getSelectedBreed().observe(getViewLifecycleOwner(), breed -> {
            if (breed != null) {
                displayBreedDetails(breed);
            }
        });
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());
        btnCareAdvice.setOnClickListener(v -> navigateToCareAdvice());
    }

    private void displayBreedDetails(Dog breed) {
        tvBreedName.setText(breed.getName());
        tvBreedDescription.setText(breed.getDescription());

        if (breed.getImageUrl() != null && !breed.getImageUrl().isEmpty()) {
            Glide.with(this)
                    .load(breed.getImageUrl())
                    .placeholder(R.drawable.rounded_corners)
                    .into(ivBreedImage);
        }

        addCharacteristic("Размер", breed.getSize() != null ? breed.getSize() : "Средний");
        addCharacteristic("Активность", breed.getActivityLevel() != null ? breed.getActivityLevel() : "Высокая");
        addCharacteristic("Подходит для семьи", "Да");
        addCharacteristic("Дрессировка", "Легкая");
        addCharacteristic("Линька", "Умеренная");
        addCharacteristic("Охранные качества", "Средние");

    }

    private void addCharacteristic(String title, String value) {
        View characteristicView = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_characteristic, layoutCharacteristics, false);

        TextView tvTitle = characteristicView.findViewById(R.id.tv_title);
        TextView tvValue = characteristicView.findViewById(R.id.tv_value);

        tvTitle.setText(title);
        tvValue.setText(value);

        layoutCharacteristics.addView(characteristicView);
    }

    private void navigateToCareAdvice() {
        CareAdviceFragment fragment = new CareAdviceFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("care_advice")
                .commit();
    }
}
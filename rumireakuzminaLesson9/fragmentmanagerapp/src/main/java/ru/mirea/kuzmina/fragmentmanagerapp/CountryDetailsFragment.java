package ru.mirea.kuzmina.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CountryDetailsFragment extends Fragment {

    private SharedViewModel viewModel;
    private TextView textViewCountryName;
    private TextView textViewCapital;
    private TextView textViewPopulation;
    private TextView textViewDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewCountryName = view.findViewById(R.id.textViewCountryName);
        textViewCapital = view.findViewById(R.id.textViewCapital);
        textViewPopulation = view.findViewById(R.id.textViewPopulation);
        textViewDescription = view.findViewById(R.id.textViewDescription);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSelectedCountry().observe(getViewLifecycleOwner(), country -> {
            if (country != null) {
                updateUI(country);
            }
        });
    }

    private void updateUI(Country country) {
        textViewCountryName.setText(country.getName());
        textViewCapital.setText("Столица: " + country.getCapital());
        textViewPopulation.setText("Население: " + formatPopulation(country.getPopulation()));
        textViewDescription.setText(country.getDescription());
    }

    private String formatPopulation(long population) {
        if (population >= 1_000_000) {
            return String.format("%.1f млн", population / 1_000_000.0);
        }
        return String.valueOf(population);
    }
}
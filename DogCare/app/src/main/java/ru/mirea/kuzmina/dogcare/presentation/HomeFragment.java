package ru.mirea.kuzmina.dogcare.presentation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.databinding.FragmentHomeBinding;
import ru.mirea.kuzmina.dogcare.presentation.adapters.BreedsAdapter;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private MainViewModel mainViewModel;
    private BreedsAdapter breedsAdapter;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        setupViewModel();
        initViews();
        setupObservers();
        setupRecyclerView();
    }

    private void setupViewModel() {
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    private void initViews() {
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
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

        binding.errorTextView.setOnClickListener(v -> mainViewModel.retryLoading());
    }

    private void setupObservers() {
        mainViewModel.getBreeds().observe(getViewLifecycleOwner(), dogs -> {
            if (dogs != null) {
                breedsAdapter.updateBreeds(dogs);
                if (dogs.isEmpty()) {
                    binding.emptyTextView.setVisibility(View.VISIBLE);
                    binding.breedsRecyclerView.setVisibility(View.GONE);
                } else {
                    binding.emptyTextView.setVisibility(View.GONE);
                    binding.breedsRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        mainViewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                binding.errorTextView.setText(error);
                binding.errorTextView.setVisibility(View.VISIBLE);
                binding.breedsRecyclerView.setVisibility(View.GONE);
                binding.emptyTextView.setVisibility(View.GONE);
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();
            } else {
                binding.errorTextView.setVisibility(View.GONE);
            }
        });

        mainViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                binding.breedsRecyclerView.setVisibility(View.GONE);
                binding.errorTextView.setVisibility(View.GONE);
                binding.emptyTextView.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecyclerView() {
        breedsAdapter = new BreedsAdapter(new ArrayList<>(), breed -> {
            mainViewModel.selectBreed(breed);
            navigateToBreedDetails();
        });

        binding.breedsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.breedsRecyclerView.setAdapter(breedsAdapter);
    }

    private void navigateToBreedDetails() {
        navController.navigate(R.id.action_home_to_breedDetails);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
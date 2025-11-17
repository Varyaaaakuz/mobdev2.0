package ru.mirea.kuzmina.dogcare.presentation.fragments;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.mirea.kuzmina.domain.models.Dog;
import ru.mirea.kuzmina.dogcare.R;
import ru.mirea.kuzmina.dogcare.presentation.MainViewModel;

public class CareAdviceFragment extends Fragment {

    private MainViewModel viewModel;
    private TextView tvBreedName, tvCareAdvice;
    private Button btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_care_advice, container, false);
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
        tvCareAdvice = view.findViewById(R.id.tv_care_advice);
        btnBack = view.findViewById(R.id.btn_back);
    }

    private void setupObservers() {
        viewModel.getSelectedBreed().observe(getViewLifecycleOwner(), breed -> {
            if (breed != null) {
                displayCareAdvice(breed);
            }
        });
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void displayCareAdvice(Dog breed) {
        tvBreedName.setText(breed.getName());

        String careAdvice = generateDetailedCareAdvice(breed);
        tvCareAdvice.setText(careAdvice);
    }

    private String generateDetailedCareAdvice(Dog breed) {
        StringBuilder advice = new StringBuilder();

        advice.append("Уход за ").append(breed.getName()).append("\n\n");

        advice.append("ПИТАНИЕ:\n");
        advice.append("• Сбалансированный корм премиум-класса\n");
        advice.append("• 2-3 кормления в день для взрослой собаки\n");
        advice.append("• Свежая вода в постоянном доступе\n");
        advice.append("• Контроль веса и порций\n\n");

        advice.append("ФИЗИЧЕСКАЯ АКТИВНОСТЬ:\n");
        if ("Высокая".equals(breed.getActivityLevel())) {
            advice.append("• Ежедневные активные прогулки 1.5-2 часа\n");
            advice.append("• Бег, апортировка, плавание\n");
            advice.append("• Интеллектуальные игры и тренировки\n");
        } else if ("Средняя".equals(breed.getActivityLevel())) {
            advice.append("• Прогулки 1-1.5 часа в день\n");
            advice.append("• Умеренные физические нагрузки\n");
            advice.append("• Игры на свежем воздухе\n");
        } else {
            advice.append("• Спокойные прогулки 30-45 минут\n");
            advice.append("• Не требующие больших нагрузок игры\n");
        }
        advice.append("\n");

        advice.append("УХОД ЗА ШЕРСТЬЮ:\n");
        advice.append("• Расчесывание 2-3 раза в неделю\n");
        advice.append("• Купание 1 раз в 2-3 месяца\n");
        advice.append("• Стрижка когтей по мере необходимости\n");
        advice.append("• Чистка ушей 1 раз в неделю\n\n");

        advice.append("ЗДОРОВЬЕ:\n");
        advice.append("• Регулярные профилактические осмотры\n");
        advice.append("• Вакцинация по графику\n");
        advice.append("• Обработка от паразитов\n");
        if ("Крупная".equals(breed.getSize())) {
            advice.append("• Контроль за состоянием суставов\n");
        }
        advice.append("\n");

        advice.append("ДРЕССИРОВКА И СОЦИАЛИЗАЦИЯ:\n");
        advice.append("• Начинать с раннего возраста\n");
        advice.append("• Положительное подкрепление\n");
        advice.append("• Регулярные тренировки\n");
        advice.append("• Социализация с другими собаками\n\n");

        return advice.toString();
    }
}
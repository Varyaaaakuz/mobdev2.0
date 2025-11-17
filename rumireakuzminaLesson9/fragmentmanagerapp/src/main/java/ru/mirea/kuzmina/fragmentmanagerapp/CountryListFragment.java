package ru.mirea.kuzmina.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.List;

public class CountryListFragment extends Fragment {
    private SharedViewModel viewModel;
    private List<Country> countries;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setupCountries();
        setupListView(view);
    }

    private void setupCountries() {
        countries = new ArrayList<>();
        countries.add(new Country("Россия", "Москва", 146000000,
                "Крупнейшее государство в мире по территории, расположенное в Восточной Европе и Северной Азии. " ));

        countries.add(new Country("Германия", "Берлин", 83000000,
                "Федеративное государство в Центральной Европе с развитой экономикой. "));

        countries.add(new Country("Франция", "Париж", 68000000,
                "Столица моды, искусства и гастрономии."));

        countries.add(new Country("Япония", "Токио", 125000000,
                "Островное государство в Восточной Азии, известное высокими технологиями. "));

        countries.add(new Country("Китай", "Пекин", 1400000000,
                "Самое населенное государство мира с быстрорастущей экономикой. "));

        countries.add(new Country("Великобритания", "Лондон", 67000000,
                "Островное государство в Западной Европе. "));

        countries.add(new Country("Италия", "Рим", 59000000,
                "Страна в Южной Европе с богатым культурным наследием. "));

        countries.add(new Country("Испания", "Мадрид", 47000000,
                "Государство на юго-западе Европы на Пиренейском полуострове."));
    }

    private void setupListView(View view) {
        ListView listView = view.findViewById(R.id.listViewCountries);
        List<String> countryNames = new ArrayList<>();
        for (Country country : countries) {
            countryNames.add(country.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                countryNames
        );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country selectedCountry = countries.get(position);
                viewModel.selectCountry(selectedCountry);
            }
        });
    }
}
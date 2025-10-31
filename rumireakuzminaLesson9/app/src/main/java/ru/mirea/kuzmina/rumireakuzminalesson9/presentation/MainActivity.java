package ru.mirea.kuzmina.rumireakuzminalesson9.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kuzmina.rumireakuzminalesson9.R;
import ru.mirea.kuzmina.domain.models.Movie;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private EditText text;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(MainActivity.class.getSimpleName(), "MainActivity created");
        text = findViewById(R.id.editTextMovie);
        textView = findViewById(R.id.textViewMovie);
        //инициализация через фабрику
        mainViewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(MainViewModel.class);
        //изменения LiveData
        mainViewModel.getFavoriteMovie().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        setupClickListeners();
    }
    private void setupClickListeners() {
        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.setText(new Movie(2, text.getText().toString()));
            }
        });
        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getText();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.class.getSimpleName(), "MainActivity destroyed");
    }
}
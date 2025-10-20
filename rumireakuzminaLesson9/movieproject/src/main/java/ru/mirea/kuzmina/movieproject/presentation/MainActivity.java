package ru.mirea.kuzmina.movieproject.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.kuzmina.movieproject.R;
import ru.mirea.kuzmina.data.repository.MovieRepositoryImpl;
import ru.mirea.kuzmina.data.storage.MovieStorage;
import ru.mirea.kuzmina.data.storage.sharedprefs.SharedPrefMovieStorage;
import ru.mirea.kuzmina.domain.repository.MovieRepository;
import ru.mirea.kuzmina.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.kuzmina.domain.usecases.SaveMovieToFavoriteUseCase;
import ru.mirea.kuzmina.domain.models.Movie;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем storage и передаем в репозиторий
        MovieStorage movieStorage = new SharedPrefMovieStorage(this);
        MovieRepository movieRepository = new MovieRepositoryImpl(movieStorage);

        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = new SaveMovieToFavoriteUseCase(movieRepository)
                        .execute(new Movie(2, text.getText().toString()));
                textView.setText(String.format("Save result %s", result));
            }
        });

        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
                textView.setText(String.format("Saved movie: %s", movie.getName()));
            }
        });
    }
}
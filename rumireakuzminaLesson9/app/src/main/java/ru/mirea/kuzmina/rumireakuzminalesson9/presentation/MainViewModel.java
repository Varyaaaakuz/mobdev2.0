package ru.mirea.kuzmina.rumireakuzminalesson9.presentation;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.kuzmina.domain.models.Movie;
import ru.mirea.kuzmina.domain.repository.MovieRepository;
import ru.mirea.kuzmina.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.kuzmina.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<String> favoriteMovie = new MutableLiveData<>();
    public MainViewModel(MovieRepository movieRepository) {
        Log.d(MainViewModel.class.getSimpleName(), "MainViewModel created");
        this.movieRepository = movieRepository;
    }
    public MutableLiveData<String> getFavoriteMovie() {
        return favoriteMovie;
    }
    @Override
    protected void onCleared() {
        Log.d(MainViewModel.class.getSimpleName(), "MainViewModel cleared");
        super.onCleared();
    }
    public void setText(Movie movie) {
        Boolean result = new SaveMovieToFavoriteUseCase(movieRepository).execute(movie);
        favoriteMovie.setValue(result.toString());
    }
    public void getText() {
        Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
        favoriteMovie.setValue(String.format("Мой любимый фильм: %s", movie.getName()));
    }
}
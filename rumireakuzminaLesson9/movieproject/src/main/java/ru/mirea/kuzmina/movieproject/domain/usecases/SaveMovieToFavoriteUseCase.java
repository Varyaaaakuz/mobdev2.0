package ru.mirea.kuzmina.movieproject.domain.usecases;

import ru.mirea.kuzmina.movieproject.domain.models.Movie;
import ru.mirea.kuzmina.movieproject.domain.repository.MovieRepository;
public class SaveMovieToFavoriteUseCase {
    private MovieRepository movieRepository;
    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
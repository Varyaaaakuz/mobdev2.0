package ru.mirea.kuzmina.domain.usecases;

import ru.mirea.kuzmina.domain.models.Movie;
import ru.mirea.kuzmina.domain.repository.MovieRepository;
public class SaveMovieToFavoriteUseCase {
    private MovieRepository movieRepository;
    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}
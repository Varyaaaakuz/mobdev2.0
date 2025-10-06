package ru.mirea.kuzmina.movieproject.domain.usecases;

import ru.mirea.kuzmina.movieproject.domain.models.Movie;
import ru.mirea.kuzmina.movieproject.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}

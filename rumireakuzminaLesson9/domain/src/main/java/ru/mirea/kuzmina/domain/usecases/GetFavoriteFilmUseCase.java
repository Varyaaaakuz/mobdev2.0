package ru.mirea.kuzmina.domain.usecases;

import ru.mirea.kuzmina.domain.models.Movie;
import ru.mirea.kuzmina.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}

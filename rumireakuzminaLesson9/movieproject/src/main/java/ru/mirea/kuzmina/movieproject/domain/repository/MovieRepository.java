package ru.mirea.kuzmina.movieproject.domain.repository;
import ru.mirea.kuzmina.movieproject.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}
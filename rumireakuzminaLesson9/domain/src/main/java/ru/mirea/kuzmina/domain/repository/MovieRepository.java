package ru.mirea.kuzmina.domain.repository;
import ru.mirea.kuzmina.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}
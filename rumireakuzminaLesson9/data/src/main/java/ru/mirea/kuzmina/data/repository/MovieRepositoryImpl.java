package ru.mirea.kuzmina.data.repository;

import java.time.LocalDate;

import ru.mirea.kuzmina.data.storage.MovieStorage;
import ru.mirea.kuzmina.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private final MovieStorage movieStorage;
    public MovieRepositoryImpl(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }
    @Override
    public boolean saveMovie(ru.mirea.kuzmina.domain.models.Movie domainMovie) {
        ru.mirea.kuzmina.data.storage.models.Movie dataMovie = mapToStorage(domainMovie);
        return movieStorage.save(dataMovie);
    }
    @Override
    public ru.mirea.kuzmina.domain.models.Movie getMovie() {
        ru.mirea.kuzmina.data.storage.models.Movie dataMovie = movieStorage.get();
        return mapToDomain(dataMovie);
    }
    private ru.mirea.kuzmina.data.storage.models.Movie mapToStorage(ru.mirea.kuzmina.domain.models.Movie domainMovie) {
        return new ru.mirea.kuzmina.data.storage.models.Movie(
                domainMovie.getId(),
                domainMovie.getName(),
                LocalDate.now().toString()
        );
    }
    private ru.mirea.kuzmina.domain.models.Movie mapToDomain(ru.mirea.kuzmina.data.storage.models.Movie dataMovie) {
        return new ru.mirea.kuzmina.domain.models.Movie(
                dataMovie.getId(),
                dataMovie.getName()
        );
    }
}
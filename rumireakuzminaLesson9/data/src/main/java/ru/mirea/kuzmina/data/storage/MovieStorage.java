package ru.mirea.kuzmina.data.storage;

import ru.mirea.kuzmina.data.storage.models.Movie;

public interface MovieStorage {
    boolean save(Movie movie);
    Movie get();
}
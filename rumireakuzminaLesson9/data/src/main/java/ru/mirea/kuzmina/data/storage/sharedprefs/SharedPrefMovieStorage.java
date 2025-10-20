package ru.mirea.kuzmina.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import java.time.LocalDate;

import ru.mirea.kuzmina.data.storage.MovieStorage;
import ru.mirea.kuzmina.data.storage.models.Movie;

public class SharedPrefMovieStorage implements MovieStorage {
    private static final String SHARED_PREFS_NAME = "movie_storage";
    private static final String KEY_NAME = "movie_name";
    private static final String KEY_DATE = "movie_date";
    private static final String KEY_ID = "movie_id";
    private SharedPreferences sharedPreferences;
    public SharedPrefMovieStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }
    @Override
    public Movie get() {
        String movieName = sharedPreferences.getString(KEY_NAME, "unknown");
        String movieDate = sharedPreferences.getString(KEY_DATE, String.valueOf(LocalDate.now()));
        int movieId = sharedPreferences.getInt(KEY_ID, -1);
        return new Movie(movieId, movieName, movieDate);
    }
    @Override
    public boolean save(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, movie.getName());
        editor.putString(KEY_DATE, String.valueOf(LocalDate.now()));
        editor.putInt(KEY_ID, movie.getId());
        return editor.commit();
    }
}
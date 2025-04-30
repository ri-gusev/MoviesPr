package com.example.movies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM favourite_movie")
    LiveData<List<Movie>> getAllFavouritesMovies();

    @Query("SELECT * FROM favourite_movie WHERE id = :id")
    LiveData<Movie> getFavouriteMovie(int id);

    @Insert
    Completable insert(Movie movie);

    @Query("DELETE FROM favourite_movie WHERE id = :id")
    Completable delete(int id);
}

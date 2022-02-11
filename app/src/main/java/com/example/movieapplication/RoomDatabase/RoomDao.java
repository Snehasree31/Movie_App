package com.example.movieapplication.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movieapplication.Retrofit.Movie;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getFavMovies();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMovies(Movie... movies);

}
package com.example.movieapplication.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.movieapplication.Retrofit.Movie;

public class AddMovies extends AsyncTask<Movie,Void,Void> {

    public RoomDao roomDao;

    public AddMovies(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        roomDao.addMovies(movies[0]);
        return null;
    }

}
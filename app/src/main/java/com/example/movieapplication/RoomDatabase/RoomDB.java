package com.example.movieapplication.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movieapplication.Retrofit.Movie;

@Database(entities={Movie.class},version = 1)
public abstract class RoomDB extends RoomDatabase  {

    public abstract RoomDao RoomDao();

    private static RoomDB roomDBInstance;

    public static RoomDB getdatabase(Context context){
        if (roomDBInstance == null){
            roomDBInstance= Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,"Database")
                    .allowMainThreadQueries()
                    .build();
        }
        return roomDBInstance;
    }
}

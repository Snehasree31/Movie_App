package com.example.movieapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.movieapplication.Retrofit.Adaptery;
import com.example.movieapplication.Retrofit.LoadMovieListerner;
import com.example.movieapplication.Retrofit.Movie;
import com.example.movieapplication.Retrofit.MovieApi;
import com.example.movieapplication.Retrofit.MovieResponse;
import com.example.movieapplication.Retrofit.RetrofitBuilder;
import com.example.movieapplication.RoomDatabase.AddMovies;
import com.example.movieapplication.RoomDatabase.RoomDB;
import com.example.movieapplication.RoomDatabase.RoomDao;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDisplayActivity extends AppCompatActivity implements LoadMovieListerner {
    public static final String BASE_URl = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "fa779c729976b2c38ea18630f36cc8e1";
    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=fa779c729976b2c38ea18630f36cc8e1";

    RecyclerView recyclerView;
    Adaptery recyclerAdapter;
    List<Movie> movieList;
    List<Movie> favmovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrofit
        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new Adaptery(getApplicationContext(), movieList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setItemClickListener(this);
        loadmovielist();

        MovieApi api = RetrofitBuilder.getdata().create(MovieApi.class);
        Call<MovieResponse> call = api.getMovies(API_KEY, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieList = response.body().getMovies();
                setMovieList(movieList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.movielistclicked:
                setMovieList(movieList);
                break;

            case R.id.favlist:
                setMovieList(favmovies);
                break;

            case R.id.Logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Logout Successful!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, StartActivity.class));
                break;

            default:
                return false;
        }
        return true;
    }

    public void setMovieList(List<Movie> List) {
        recyclerAdapter.setMovielist(List);
    }

    @Override
    public void OnMovieClick(int position) {
        Toast.makeText(this, movieList.get(position).title+" added to Favourites", Toast.LENGTH_SHORT).show();
        RoomDao roomDao = RoomDB.getdatabase(this).RoomDao();
        new AddMovies(roomDao).execute(movieList.get(position));
    }

    private void loadmovielist() {
        RoomDB favroomDB = RoomDB.getdatabase(this.getApplicationContext());
        favroomDB.RoomDao().getFavMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                favmovies=new ArrayList<>();
                favmovies.addAll(movies);
            }
        });
    }
}
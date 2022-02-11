package com.example.movieapplication.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("https://api.themoviedb.org/3/movie/popular")
    Call<MovieResponse> getMovies(
            @Query("api_key") String key,
            @Query("page") int page
    );
}

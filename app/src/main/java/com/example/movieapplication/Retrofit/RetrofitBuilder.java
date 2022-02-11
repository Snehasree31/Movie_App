package com.example.movieapplication.Retrofit;

import com.example.movieapplication.MovieDisplayActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Retrofit retrofit;
    public static Retrofit getdata(){
        if(retrofit==null){
            HttpLoggingInterceptor http=new HttpLoggingInterceptor();
            http.setLevel(HttpLoggingInterceptor.Level.BODY);

            retrofit = new Retrofit.Builder()
                    .baseUrl(MovieDisplayActivity.BASE_URl)
                    .client(new OkHttpClient().newBuilder().addInterceptor(http).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}

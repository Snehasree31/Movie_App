package com.example.movieapplication.Retrofit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.movieapplication.MovieDisplayActivity;
import com.example.movieapplication.R;
import com.example.movieapplication.RoomDatabase.RoomDB;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.ViewHolder> {

    Context context;
    List<Movie> movieList;
    LoadMovieListerner loadmovielisterner;

    public Adaptery(Context context, List<Movie> movielist) {

        this.context = context;
        this.movieList = movielist;

    }

    public void setMovielist(List<Movie> movielist){
        this.movieList=new ArrayList<>();
        this.movieList.addAll(movielist);
        notifyDataSetChanged();
    }

    @Override
    public Adaptery.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movielist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptery.ViewHolder holder, int position) {
        holder.Movie_name.setText(movieList.get(position).getTitle().toString());
        holder.Movie_rating.setText(String.valueOf(movieList.get(position).getVoteAverage()));
        //      Using Picasso
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).getPosterPath())
                .into(holder.Movie_image);
        holder.btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadmovielisterner.OnMovieClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(movieList != null){
            return movieList.size();
        }
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView Movie_name, Movie_rating;
        ImageView Movie_image;
        Button btnfav;


        public ViewHolder(View itemView) {
            super(itemView);
            Movie_name = (TextView) itemView.findViewById(R.id.Movie_name);
            Movie_rating = (TextView) itemView.findViewById(R.id.Movie_rating);
            Movie_image = (ImageView) itemView.findViewById(R.id.Movie_image);
            btnfav = (Button) itemView.findViewById(R.id.btnfav);

        }
    }
    public void setItemClickListener(LoadMovieListerner clickListener)
    {
        loadmovielisterner = clickListener;
    }

}

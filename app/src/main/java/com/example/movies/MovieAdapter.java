package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHoder>{

    private List<Movie> movieList = new ArrayList<>();

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MovieViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHoder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);
        holder.textViewRating.setText(
                String.valueOf(Math.round(movie.getRating().getKinopoiskRating()*10.0)/10.0));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHoder extends RecyclerView.ViewHolder{

        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MovieViewHoder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.ImageViewPoster);
            textViewRating = itemView.findViewById(R.id.TextViewRating);
        }
    }
}

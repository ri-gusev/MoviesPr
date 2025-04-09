package com.example.movies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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

        double rating =  movie.getRating().getKinopoiskRating();
        int backgroundId;

        if (rating < 4.5){
            backgroundId = R.drawable.circle_red;
        } else if (rating > 4.5 && rating < 6.5){
            backgroundId = R.drawable.circle_orange;
        } else {
            backgroundId = R.drawable.circle_green;
        }

        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewRating.setBackground(background);

        holder.textViewRating.setText(String.valueOf(Math.round(rating*10.0)/10.0));
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

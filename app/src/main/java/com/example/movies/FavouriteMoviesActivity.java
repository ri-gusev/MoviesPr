package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouriteMoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);
        initViews();

        FavouriteMoviesViewModel favouriteMoviesViewModel = new ViewModelProvider(this).get(
                FavouriteMoviesViewModel.class);

        favouriteMoviesViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovieList(movies);
            }
        });

        movieAdapter.setOnClickListener(new MovieAdapter.onClickListener() {
            @Override
            public void onClick(Movie movie) {
                startActivity(MovieDetailActivity.newIntent(
                        FavouriteMoviesActivity.this,
                        movie));
            }
        });
    }

    public static Intent newIntent(Context context){
        return new Intent(context, FavouriteMoviesActivity.class);
    }

    public void initViews() {
        recyclerView = findViewById(R.id.RecyclerViewFavouriteMovies);
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
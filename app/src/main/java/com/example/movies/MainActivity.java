package com.example.movies;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import static com.example.movies.MovieDetailActivity.newIntent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initViews();

        movieAdapter = new MovieAdapter();

        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovieList(movies);
            }
        });

        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading){
                    progressBar.setVisibility(VISIBLE);
                } else {
                    progressBar.setVisibility(GONE);
                }
            }
        });

        movieAdapter.setOnClickListener(new MovieAdapter.onClickListener() {
            @Override
            public void onClick(Movie movie) {
                Intent intent = newIntent(MainActivity.this, movie);
                startActivity(intent);
            }
        });

        movieAdapter.setOnReachEndListener(new MovieAdapter.onReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadMoviesFromInternet();
            }
        });

    }

    public void initViews(){
        progressBar = findViewById(R.id.ProgressBarLoading);
        recyclerView = findViewById(R.id.RecyclerViewMovies);
    }

}
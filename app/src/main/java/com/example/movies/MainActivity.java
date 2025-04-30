package com.example.movies;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import static com.example.movies.MovieDetailActivity.newIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();

        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

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
        movieAdapter = new MovieAdapter();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        progressBar = findViewById(R.id.ProgressBarLoading);
        recyclerView = findViewById(R.id.RecyclerViewMovies);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(
                R.menu.main_menu,
                menu
        );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favouriteMovies){
            startActivity(FavouriteMoviesActivity.newIntent(this));
        }
        return super.onOptionsItemSelected(item);
    }
}
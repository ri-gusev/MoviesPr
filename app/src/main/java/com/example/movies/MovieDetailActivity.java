package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "Movie";

    private MovieDetailViewModel movieDetailViewModel;
    private TrailerAdapter trailerAdapter;
    private RecyclerView recyclerView;

    private ImageView imageViewPosterDetail;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_movie);
        initViews();

        recyclerView.setAdapter(trailerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                        .load(movie.getPoster().getUrl())
                        .into(imageViewPosterDetail);
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());
        textViewTitle.setText(movie.getName());

        movieDetailViewModel.loadTrailers(movie.getId());

        movieDetailViewModel.getListMutableLiveData().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailerAdapter.setTrailerList(trailers);
            }
        });
    }



    private void initViews(){
        trailerAdapter = new TrailerAdapter();
        recyclerView = findViewById(R.id.RecyclerViewTrailers);
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        imageViewPosterDetail = findViewById(R.id.ImageViewPosterDetail);
        textViewTitle = findViewById(R.id.TextViewTitle);
        textViewYear = findViewById(R.id.TextViewYear);
        textViewDescription = findViewById(R.id.TextViewDescription);
    }

    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("Movie", movie);
        return intent;
    }
}
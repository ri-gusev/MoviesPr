package com.example.movies;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
    private RecyclerView recyclerViewTrailer;

    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerViewReview;
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

        recyclerViewTrailer.setAdapter(trailerAdapter);
        recyclerViewTrailer.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewReview.setAdapter(reviewAdapter);
        recyclerViewReview.setLayoutManager(new LinearLayoutManager(this));

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                        .load(movie.getPoster().getUrl())
                        .into(imageViewPosterDetail);
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());
        textViewTitle.setText(movie.getName());

        movieDetailViewModel.loadTrailers(movie.getId());

        movieDetailViewModel.getTrailerLiveData().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailerAdapter.setTrailerList(trailers);
            }
        });

        movieDetailViewModel.loadReviews(movie.getId());

        movieDetailViewModel.getReviewLiveData().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.setReviewList(reviews);
            }
        });

        trailerAdapter.setOnTrailerClickListener(new TrailerAdapter.onTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });

        //Just to check does the code work correctly (it is)
        MovieDao movieDao = MovieDatabase.getInstance(getApplication()).movieDao();
        movieDao.insert(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();

    }



    private void initViews(){
        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailer = findViewById(R.id.RecyclerViewTrailers);
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        reviewAdapter = new ReviewAdapter();
        recyclerViewReview = findViewById(R.id.RecyclerViewReviews);

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
package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "Movie";

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

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                        .load(movie.getPoster().getUrl())
                        .into(imageViewPosterDetail);
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());
        textViewTitle.setText(movie.getName());

        Disposable disposable = ApiFactory.apiService.loadTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrailersResponse>() {
                    @Override
                    public void accept(TrailersResponse trailersResponse) throws Throwable {
                        Log.d("MovieDetailActivity", trailersResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("MovieDetailActivity", throwable.toString());
                    }
                });
    }



    private void initViews(){
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
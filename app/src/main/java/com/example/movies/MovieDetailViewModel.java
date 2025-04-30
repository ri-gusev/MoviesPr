package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {

    private static final String TAG = "MovieDetailViewModel";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Trailer>> trailerLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Review>> reviewLiveData = new MutableLiveData<>();

    private MovieDao movieDao;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavouriteMovie(int movieId){
        return movieDao.getFavouriteMovie(movieId);
    }

    public LiveData<List<Trailer>> getTrailerLiveData() {
        return trailerLiveData;
    }

    public LiveData<List<Review>> getReviewLiveData() {
        return reviewLiveData;
    }

    public void loadTrailers(int id){
        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailersResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailersResponse trailersResponse) throws Throwable {
                        return trailersResponse.getTrailersList().getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailers) throws Throwable {
                        trailerLiveData.setValue(trailers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertMovie(Movie movie){
        Disposable disposable = movieDao.insert(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void deleteMovie(int id){
        Disposable disposable = movieDao.delete(id)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }


    public void loadReviews(int id){
        Disposable disposable = ApiFactory.apiService.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewList, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewList reviewList) throws Throwable {
                        return reviewList.getReviewList();
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviews) throws Throwable {
                        reviewLiveData.setValue(reviews);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("MovieDetailActivity", throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

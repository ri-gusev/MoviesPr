package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?limit=40&notNullFields=rating.kp&notNullFields=poster.url&notNullFields=description&rating.kp=4-10&sortField=votes.kp&sortType=-1&type=movie")
    @Headers("X-API-KEY:your_key")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}

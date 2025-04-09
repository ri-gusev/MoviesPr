package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?limit=100&notNullFields=rating.kp&notNullFields=poster.url&notNullFields=description&sortField=rating.kp&sortType=-1&sortField=votes.kp&sortType=-1&type=movie")
    @Headers("X-API-KEY:85AJZ12-GWY42H6-J2HC2MH-A4NVTHS")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}

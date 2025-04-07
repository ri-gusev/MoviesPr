package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @GET("movie?page=1&limit=1&notNullFields=rating.imdb&notNullFields=poster.url&notNullFields=description&sortField=rating.kp&sortType=-1&sortField=votes.kp&sortType=-1")
    @Headers("X-API-KEY:85AJZ12-GWY42H6-J2HC2MH-A4NVTHS")
    Single<MovieResponse> loadMovies();
}

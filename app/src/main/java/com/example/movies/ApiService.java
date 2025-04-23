package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?limit=40&notNullFields=rating.kp&notNullFields=poster.url&notNullFields=description&rating.kp=4-10&sortField=votes.kp&sortType=-1&type=movie")
    @Headers("X-API-KEY:85AJZ12-GWY42H6-J2HC2MH-A4NVTHS")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie/{id}")
    @Headers("X-API-KEY:85AJZ12-GWY42H6-J2HC2MH-A4NVTHS")
    Single<TrailersResponse> loadTrailers(@Path("id") int id);

    @GET("review?page=1&limit=7")
    @Headers("X-API-KEY:85AJZ12-GWY42H6-J2HC2MH-A4NVTHS")
    Single<ReviewList> loadReviews(@Query("movieId") int id);
}

package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    private double kinopoiskRating;

    public Rating(int kp) {
        this.kinopoiskRating = kp;
    }

    public double getKinopoiskRating() {
        return kinopoiskRating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kinopoiskRating=" + kinopoiskRating +
                '}';
    }
}

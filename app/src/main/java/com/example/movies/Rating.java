package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("kp")
    private double kinopoiskRating;

    public Rating() {
    }

    public void setKinopoiskRating(double kinopoiskRating) {
        this.kinopoiskRating = kinopoiskRating;
    }

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

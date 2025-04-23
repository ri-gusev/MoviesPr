package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    @SerializedName("author")
    private String author;

    @SerializedName("type")
    private String FilmType;

    @SerializedName("review")
    private String review;

    public Review(String author, String filmType, String review) {
        this.author = author;
        FilmType = filmType;
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public String getFilmType() {
        return FilmType;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", FilmType='" + FilmType + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}

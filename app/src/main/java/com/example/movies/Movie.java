package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("year")
    private int year;
    @SerializedName("description")
    private String description;
    @SerializedName("rating")
    private Rating rating;
    @SerializedName("poster")
    private Poster poster;

    public Movie(String description, int id, String name, Poster poster, Rating rating, int year) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.rating = rating;
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", poster=" + poster +
                '}';
    }
}

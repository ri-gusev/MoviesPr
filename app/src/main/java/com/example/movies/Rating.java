package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    private int kp;

    public Rating(int kp) {
        this.kp = kp;
    }

    public int getKp() {
        return kp;
    }
}

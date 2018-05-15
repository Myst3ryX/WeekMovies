package com.myst3ry.weekmovies.model;

import java.io.Serializable;
import java.util.List;

public final class Actor implements Serializable {

    private String photoUrl;
    private String name;
    private String birthdayDate;
    private String biography;
    private List<Movie> movies;


    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public String getBiography() {
        return biography;
    }

    public List<Movie> getMovies() {
        return movies;
    }


    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

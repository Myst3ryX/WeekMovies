package com.myst3ry.weekmovies.model;

//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Index;
//import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import java.util.List;

//@SuppressWarnings("serial")
//@Entity(indexes = {@Index(value = "name DESC")})
public final class Actor implements Serializable {

    //@Id
    //private Long id;

    private String photoUrl;

    //@NotNull
    private String name;

    //@NotNull
    private String birthdayDate;

    private String biography;

    private List<Movie> movies;

    public Actor(String photoUrl, String name, String birthdayDate, String biography, List<Movie> movies) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.biography = biography;
        this.movies = movies;
    }

    //    public Long getId() {
//        return id;
//    }

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


//    public void setId(Long id) {
//        this.id = id;
//    }

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

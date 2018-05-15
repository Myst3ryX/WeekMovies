package com.myst3ry.weekmovies.model;

//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.annotation.Id;
//import org.greenrobot.greendao.annotation.Index;
//import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import java.util.List;

//@SuppressWarnings("serial")
//@Entity(indexes = {@Index(value = "title DESC")})
public final class Movie implements Serializable {

    //@Id
    //private Long id;

    private String coverUrl;

    //@NotNull
    private String title;

    //@NotNull
    private String releaseDate;

    private String description;

    private List<Actor> actors;

    public Movie(String coverUrl, String title, String releaseDate, String description, List<Actor> actors) {
        this.coverUrl = coverUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.actors = actors;
    }

    //    public Long getId() {
//        return id;
//    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public List<Actor> getActors() {
        return actors;
    }


//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
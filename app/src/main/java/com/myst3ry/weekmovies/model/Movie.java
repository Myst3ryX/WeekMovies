package com.myst3ry.weekmovies.model;

import java.io.Serializable;
import java.util.List;

public final class Movie implements Serializable {

    private String coverUrl;
    private String title;
    private String releaseDate;
    private String description;
    private List<Actor> actors;


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
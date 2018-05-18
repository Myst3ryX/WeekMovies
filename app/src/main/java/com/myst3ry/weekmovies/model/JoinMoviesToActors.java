package com.myst3ry.weekmovies.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public final class JoinMoviesToActors {

    @Id
    private Long id;

    private Long movieId;

    private Long actorId;

    @Generated(hash = 951628780)
    public JoinMoviesToActors(Long id, Long movieId, Long actorId) {
        this.id = id;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    @Generated(hash = 1559212219)
    public JoinMoviesToActors() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return this.movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getActorId() {
        return this.actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }
}

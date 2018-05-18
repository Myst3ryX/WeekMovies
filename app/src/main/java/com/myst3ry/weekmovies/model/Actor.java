package com.myst3ry.weekmovies.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Entity(indexes = {@Index(value = "name DESC")})
public final class Actor implements Serializable {

    @Id
    private Long id;

    private String photoUrl;

    @NotNull
    private String name;

    @NotNull
    private String birthdayDate;

    private String biography;

    @ToMany
    @JoinEntity(
            entity = JoinMoviesToActors.class,
            sourceProperty = "actorId",
            targetProperty = "movieId")
    private List<Movie> movies;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 93232479)
    private transient ActorDao myDao;

    public Actor(Long id, String photoUrl, String name, String birthdayDate, String biography, List<Movie> movies) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.biography = biography;
        this.movies = movies;
    }

    @Generated(hash = 266699458)
    public Actor(Long id, String photoUrl, @NotNull String name, @NotNull String birthdayDate, String biography) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.biography = biography;
    }

    @Generated(hash = 1859932406)
    public Actor() {
    }

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setBirthdayDate(@NotNull String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 470865646)
    public synchronized void resetMovies() {
        movies = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1780235118)
    public List<Movie> getMovies() {
        if (movies == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MovieDao targetDao = daoSession.getMovieDao();
            List<Movie> moviesNew = targetDao._queryActor_Movies(id);
            synchronized (this) {
                if (movies == null) {
                    movies = moviesNew;
                }
            }
        }
        return movies;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 576914310)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getActorDao() : null;
    }
}

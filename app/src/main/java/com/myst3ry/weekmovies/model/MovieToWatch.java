package com.myst3ry.weekmovies.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

@Entity
public final class MovieToWatch {

    @Id
    private Long id;

    @ToOne(joinProperty = "id")
    private Movie movie;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1172141700)
    private transient MovieToWatchDao myDao;

    @Generated(hash = 326513196)
    public MovieToWatch(Long id) {
        this.id = id;
    }

    @Generated(hash = 436017774)
    public MovieToWatch() {
    }

    @Generated(hash = 708760245)
    private transient Long movie__resolvedKey;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 912874810)
    public Movie getMovie() {
        Long __key = this.id;
        if (movie__resolvedKey == null || !movie__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MovieDao targetDao = daoSession.getMovieDao();
            Movie movieNew = targetDao.load(__key);
            synchronized (this) {
                movie = movieNew;
                movie__resolvedKey = __key;
            }
        }
        return movie;
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
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 2024828085)
    public void setMovie(Movie movie) {
        synchronized (this) {
            this.movie = movie;
            id = movie == null ? null : movie.getId();
            movie__resolvedKey = id;
        }
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1454092393)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMovieToWatchDao() : null;
    }
}

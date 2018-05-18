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
@Entity(indexes = {@Index(value = "title DESC")})
public final class Movie implements Serializable {

    @Id
    private Long id;

    private String coverUrl;

    @NotNull
    private String title;

    @NotNull
    private String releaseDate;

    private String description;

    @ToMany
    @JoinEntity(
            entity = JoinMoviesToActors.class,
            sourceProperty = "movieId",
            targetProperty = "actorId")
    private List<Actor> actors;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1042217376)
    private transient MovieDao myDao;


    public Movie(Long id, String coverUrl, String title, String releaseDate, String description, List<Actor> actors) {
        this.id = id;
        this.coverUrl = coverUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.actors = actors;
    }

    @Generated(hash = 1811821414)
    public Movie(Long id, String coverUrl, @NotNull String title, @NotNull String releaseDate,
                 String description) {
        this.id = id;
        this.coverUrl = coverUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    @Generated(hash = 1263461133)
    public Movie() {
    }

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public void setReleaseDate(@NotNull String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 122178469)
    public synchronized void resetActors() {
        actors = null;
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
    @Generated(hash = 871462899)
    public List<Actor> getActors() {
        if (actors == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActorDao targetDao = daoSession.getActorDao();
            List<Actor> actorsNew = targetDao._queryMovie_Actors(id);
            synchronized (this) {
                if (actors == null) {
                    actors = actorsNew;
                }
            }
        }
        return actors;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 215161401)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMovieDao() : null;
    }
}
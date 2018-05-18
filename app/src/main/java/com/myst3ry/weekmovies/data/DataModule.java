package com.myst3ry.weekmovies.data;

import android.app.Application;

import com.myst3ry.weekmovies.WeekMoviesApp;
import com.myst3ry.weekmovies.model.ActorDao;
import com.myst3ry.weekmovies.model.DaoSession;
import com.myst3ry.weekmovies.model.MovieDao;
import com.myst3ry.weekmovies.model.MovieToWatchDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {

    @Provides
    @Singleton
    DaoSession providesDaoSession(Application application) {
        return ((WeekMoviesApp) application).getDaoSession();
    }

    @Provides
    @Singleton
    MovieDao providesMovieDao(DaoSession daoSession) {
        return daoSession.getMovieDao();
    }

    @Provides
    @Singleton
    MovieToWatchDao providesMovieToWatchDao(DaoSession daoSession) {
        return daoSession.getMovieToWatchDao();
    }

    @Provides
    @Singleton
    ActorDao providesActorDao(DaoSession daoSession) {
        return daoSession.getActorDao();
    }
}
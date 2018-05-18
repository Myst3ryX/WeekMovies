package com.myst3ry.weekmovies;

import android.app.Application;

import com.myst3ry.weekmovies.model.DaoMaster;
import com.myst3ry.weekmovies.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public final class WeekMoviesApp extends Application {

    private DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "movies-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}

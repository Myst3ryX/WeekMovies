package com.myst3ry.weekmovies;

import android.app.Application;
import android.content.Context;

import com.myst3ry.weekmovies.data.DataComponent;
import com.myst3ry.weekmovies.data.DataModule;
import com.myst3ry.weekmovies.model.DaoMaster;
import com.myst3ry.weekmovies.model.DaoSession;
import com.myst3ry.weekmovies.network.DaggerNetworkComponent;
import com.myst3ry.weekmovies.network.NetworkComponent;
import com.myst3ry.weekmovies.network.NetworkModule;

import org.greenrobot.greendao.database.Database;

public final class WeekMoviesApp extends Application {

    private DaoSession daoSession;
    private DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        prepareDaoSession();
        prepareDaggerComponents();
    }

    private void prepareDaoSession() {
        final DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "movies-db");
        final Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    private void prepareDaggerComponents() {
        final AppModule appModule = new AppModule(this);
        final DataModule dataModule = new DataModule();
        final NetworkModule networkModule = new NetworkModule();

        final NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(networkModule)
                .build();

        dataComponent = networkComponent.dataSubcomponent(appModule, dataModule);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static DataComponent getDataComponent(Context context) {
        return ((WeekMoviesApp) context.getApplicationContext()).dataComponent;
    }
}

package com.myst3ry.weekmovies.network;

import com.myst3ry.weekmovies.AppModule;
import com.myst3ry.weekmovies.data.DataComponent;
import com.myst3ry.weekmovies.data.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    DataComponent dataSubcomponent(AppModule appModule, DataModule dataModule);
}

package com.myst3ry.weekmovies.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class NetworkModule {

    @Singleton
    @Provides
    MoviesApiMock providesApi() {
        return new MoviesApiMock();
    }
}

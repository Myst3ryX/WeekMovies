package com.myst3ry.weekmovies.ui.listeners;

import android.support.annotation.NonNull;

import com.myst3ry.weekmovies.model.Movie;

public interface OnMovieClickListener {

    void onMovieClick(@NonNull final Movie movie);
}

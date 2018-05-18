package com.myst3ry.weekmovies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.myst3ry.weekmovies.WeekMoviesApp;
import com.myst3ry.weekmovies.model.MovieDao;
import com.myst3ry.weekmovies.model.MovieToWatchDao;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    @Inject
    MovieDao movieDao;
    @Inject
    MovieToWatchDao movieToWatchDao;

    private Unbinder unbinder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WeekMoviesApp.getDataComponent(getActivity()).inject(this);
        unbinder = ButterKnife.bind(this, view);
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }

    public MovieToWatchDao getMovieToWatchDao() {
        return movieToWatchDao;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

package com.myst3ry.weekmovies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.listeners.OnMovieClickListener;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.network.MoviesApiMock;
import com.myst3ry.weekmovies.ui.adapter.MoviesAdapter;

import java.util.List;

import butterknife.BindView;

public class WeekMoviesFragment extends BaseFragment {

    private MoviesApiMock api;
    private MoviesAdapter adapter;
    private List<Movie> movies;

    @BindView(R.id.week_movies_rec_view)
    RecyclerView moviesRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        api = new MoviesApiMock(); //mock
        prepareContent();

        return inflater.inflate(R.layout.fragment_week_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.week_premieres_title));
        initAdapter();
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    private void prepareContent() {
        this.movies = api.getMovies();
    }

    private void initAdapter() {
        if (!(getActivity() instanceof OnMovieClickListener)) {
            throw new IllegalArgumentException("Activity should implements OnMovieClickListener");
        }

        adapter = new MoviesAdapter((OnMovieClickListener) getActivity());
    }

    private void updateUI() {
        if (movies != null) {
            adapter.setMovies(movies);
        }
    }
}

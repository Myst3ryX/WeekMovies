package com.myst3ry.weekmovies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeekMoviesFragment extends Fragment {

    private MoviesAdapter adapter;
    private List<Movie> movies;
    private MoviesApiMock api;
    private Unbinder unbinder;

    @BindView(R.id.week_movies_rec_view)
    RecyclerView moviesRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        api = new MoviesApiMock(); //mock

        initAdapter();

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.movies = api.getMovies();
        updateUI();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

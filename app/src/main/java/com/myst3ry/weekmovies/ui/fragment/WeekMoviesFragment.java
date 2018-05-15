package com.myst3ry.weekmovies.ui.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.listeners.OnMovieClickListener;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.network.MoviesApiMock;
import com.myst3ry.weekmovies.ui.activity.MainActivity;
import com.myst3ry.weekmovies.ui.adapter.MoviesAdapter;

import java.util.List;

import butterknife.BindView;

public class WeekMoviesFragment extends BaseFragment {

    private MoviesApiMock api;
    private MoviesAdapter adapter;
    private List<Movie> movies;

    @BindView(R.id.movies_rec_view)
    RecyclerView moviesRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.week_premieres_title));
        setHasOptionsMenu(true);
        api = new MoviesApiMock(); //mock

        initAdapter();

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesRecyclerView.setAdapter(adapter);

        getMovies();
    }

    protected void getMovies() {
        this.movies = api.getMovies();
        updateUI(movies);
    }

    private void initAdapter() {
        if (!(getActivity() instanceof OnMovieClickListener)) {
            throw new IllegalArgumentException("Activity should implements OnMovieClickListener");
        }
        adapter = new MoviesAdapter((OnMovieClickListener) getActivity());
    }

    public void updateUI(@NonNull List<Movie> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_week_movies, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                final SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
                final SearchView searchView = (SearchView) item.getActionView();

                searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
                searchView.setSubmitButtonEnabled(true);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Fragment fragment = SearchResultFragment.newInstance(query);
                        ((MainActivity) getActivity()).switchContent(fragment);

                        if (searchView.isShown()) {
                            item.collapseActionView();
                        }
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

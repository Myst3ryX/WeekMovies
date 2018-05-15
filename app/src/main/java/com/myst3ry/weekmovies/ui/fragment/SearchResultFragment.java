package com.myst3ry.weekmovies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myst3ry.weekmovies.BuildConfig;
import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.ui.adapter.MoviesAdapter;

import java.util.List;

public final class SearchResultFragment extends WeekMoviesFragment {

    private static final String ARG_SEARCH_QUERY = BuildConfig.APPLICATION_ID + ".arg.search_query";

    public static SearchResultFragment newInstance(final String searchQuery) {
        final SearchResultFragment searchResultFragment = new SearchResultFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_SEARCH_QUERY, searchQuery);
        searchResultFragment.setArguments(args);
        return searchResultFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.search_title));

        final Bundle args = getArguments();
        if (args != null) {
            final String searchQuery = getArguments().getString(ARG_SEARCH_QUERY);
            final List<Movie> movies = ((MoviesAdapter) moviesRecyclerView.getAdapter()).setFilter(searchQuery);
            updateUI(movies);
        }
    }
}

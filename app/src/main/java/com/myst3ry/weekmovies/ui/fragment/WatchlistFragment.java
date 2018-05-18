package com.myst3ry.weekmovies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.model.MovieToWatch;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public final class WatchlistFragment extends WeekMoviesFragment {

    @BindView(R.id.watchlist_empty_text)
    TextView emptyText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watchlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.watchlist_title));

        if (getMovieToWatchDao() != null) {
            final List<Movie> movies = new ArrayList<>();

            final Query<MovieToWatch> movieToWatchQuery = getMovieToWatchDao().queryBuilder().build();
            final List<MovieToWatch> watchlist = movieToWatchQuery.list();

            for (MovieToWatch mtw : watchlist) {
                movies.add(mtw.getMovie());
            }

            if (movies.isEmpty()) {
                emptyText.setVisibility(View.VISIBLE);
            } else {
                emptyText.setVisibility(View.INVISIBLE);
            }

            updateUI(movies);
        }
    }
}

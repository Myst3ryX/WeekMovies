package com.myst3ry.weekmovies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.myst3ry.weekmovies.BuildConfig;
import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.listeners.OnActorClickListener;
import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.model.MovieToWatch;
import com.myst3ry.weekmovies.model.MovieToWatchDao;
import com.myst3ry.weekmovies.network.GlideApp;
import com.myst3ry.weekmovies.ui.activity.MainActivity;
import com.myst3ry.weekmovies.ui.adapter.ActorsAdapter;

import java.util.List;

import butterknife.BindView;

public final class MovieDetailFragment extends BaseFragment {

    private static final String ARG_CURRENT_MOVIE = BuildConfig.APPLICATION_ID + ".arg.current_movie";

    private Movie movie;
    private ActorsAdapter adapter;
    private MovieToWatchDao movieToWatchDao;

    @BindView(R.id.movie_detail_cover)
    ImageView coverView;
    @BindView(R.id.movie_detail_title)
    TextView titleView;
    @BindView(R.id.movie_detail_release_date)
    TextView releaseDateView;
    @BindView(R.id.movie_detail_desc)
    TextView descriptionView;
    @BindView(R.id.movie_cast_rec_view)
    RecyclerView actorsView;
    @BindView(R.id.movie_detail_watchlist_btn)
    TextView watchlistBtn;

    public static MovieDetailFragment newInstance(final Movie movie) {
        final MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        final Bundle args = new Bundle();
        args.putSerializable(ARG_CURRENT_MOVIE, movie);
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieToWatchDao = ((MainActivity) getActivity()).getMovieToWatchDao();

        initAdapter();

        final Bundle args = getArguments();
        if (args != null) {
            movie = (Movie) getArguments().getSerializable(ARG_CURRENT_MOVIE);
            if (movie != null) {
                showContent(movie);
            }
        }

        actorsView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        actorsView.setAdapter(adapter);

        watchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInWatchlist(movie)) {
                    movieToWatchDao.deleteByKey(movie.getId());
                    watchlistBtn.setText(R.string.add_to_watchlist_btn);
                    Toast.makeText(getActivity(), String.format(getString(R.string.removed_successful),
                            movie.getTitle()), Toast.LENGTH_SHORT).show();
                } else {
                    movieToWatchDao.insertOrReplace(getMovieToWatch(movie));
                    watchlistBtn.setText(R.string.remove_from_watchlist_btn);
                    Toast.makeText(getActivity(), String.format(getString(R.string.added_successful),
                            movie.getTitle()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showContent(@NonNull final Movie movie) {

        getActivity().setTitle(movie.getTitle());

        GlideApp.with(this)
                .load(movie.getCoverUrl())
                .centerInside()
                .placeholder(R.color.color_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade(400))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(coverView);

        titleView.setText(movie.getTitle());
        releaseDateView.setText(movie.getReleaseDate());
        descriptionView.setText(movie.getDescription());

        if (isInWatchlist(movie)) {
            watchlistBtn.setText(R.string.remove_from_watchlist_btn);
        } else {
            watchlistBtn.setText(R.string.add_to_watchlist_btn);
        }

        final List<Actor> actors = movie.getActors();
        updateUI(actors);
    }

    private boolean isInWatchlist(@NonNull final Movie movie) {
        return movieToWatchDao.loadDeep(movie.getId()) != null;
    }

    private MovieToWatch getMovieToWatch(@NonNull final Movie movie) {
        final MovieToWatch movieToWatch = new MovieToWatch();
        final Long movieId = movie.getId();
        movieToWatch.setId(movieId);
        return movieToWatch;
    }

    private void initAdapter() {
        if (!(getActivity() instanceof OnActorClickListener)) {
            throw new IllegalArgumentException("Activity should implements OnActorClickListener");
        }
        adapter = new ActorsAdapter((OnActorClickListener) getActivity());
    }

    private void updateUI(@NonNull final List<Actor> actors) {
        adapter.setActors(actors);
    }
}

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

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.myst3ry.weekmovies.BuildConfig;
import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.listeners.OnActorClickListener;
import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.network.GlideApp;
import com.myst3ry.weekmovies.ui.adapter.ActorsAdapter;

import java.util.List;

import butterknife.BindView;

public final class MovieDetailFragment extends BaseFragment {

    private static final String ARG_CURRENT_MOVIE = BuildConfig.APPLICATION_ID + ".arg.current_movie";

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
    @BindView(R.id.movie_detail_add_btn)
    TextView addToWatchlistBtn;

    private Movie movie;
    private ActorsAdapter adapter;

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

        initAdapter();

        final Bundle args = getArguments();
        if (args != null) {
            movie = (Movie) getArguments().getSerializable(ARG_CURRENT_MOVIE);
            showContent();
        }

        actorsView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        actorsView.setAdapter(adapter);

        addToWatchlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add movie to DB
            }
        });
    }

    private void showContent() {
        if (movie != null) {
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

            final List<Actor> actors = movie.getActors();
            updateUI(actors);

            getActivity().setTitle(movie.getTitle());
        }
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

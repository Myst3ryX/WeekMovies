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
import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.network.GlideApp;
import com.myst3ry.weekmovies.ui.adapter.MoviesAdapter;
import com.myst3ry.weekmovies.ui.listeners.OnMovieClickListener;

import java.util.List;

import butterknife.BindView;

public final class ActorFragment extends BaseFragment {

    private static final String ARG_CURRENT_ACTOR = BuildConfig.APPLICATION_ID + ".arg.current_actor";

    @BindView(R.id.actor_detail_photo)
    ImageView photoView;
    @BindView(R.id.actor_detail_name)
    TextView nameView;
    @BindView(R.id.actor_detail_birthday_date)
    TextView birthdayDateView;
    @BindView(R.id.actor_detail_biography)
    TextView bioView;
    @BindView(R.id.actor_movies_rec_view)
    RecyclerView moviesView;

    private Actor actor;
    private List<Movie> movies;
    private MoviesAdapter adapter;

    public static ActorFragment newInstance(final Actor actor) {
        final ActorFragment actorFragment = new ActorFragment();
        final Bundle args = new Bundle();
        args.putSerializable(ARG_CURRENT_ACTOR, actor);
        actorFragment.setArguments(args);
        return actorFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();

        final Bundle args = getArguments();
        if (args != null) {
            actor = (Actor) getArguments().getSerializable(ARG_CURRENT_ACTOR);
            showContent();
        }

        moviesView.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesView.setNestedScrollingEnabled(false);
        moviesView.setAdapter(adapter);
    }

    private void showContent() {
        if (actor != null) {
            GlideApp.with(this)
                    .load(actor.getPhotoUrl())
                    .centerInside()
                    .placeholder(R.color.color_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade(400))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(photoView);
            nameView.setText(actor.getName());
            birthdayDateView.setText(actor.getBirthdayDate());
            bioView.setText(actor.getBiography());
            movies = actor.getMovies();
            updateUI();

            getActivity().setTitle(actor.getName());
        }
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

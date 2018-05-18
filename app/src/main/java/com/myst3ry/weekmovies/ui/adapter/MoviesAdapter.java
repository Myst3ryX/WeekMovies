package com.myst3ry.weekmovies.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.network.GlideApp;
import com.myst3ry.weekmovies.ui.listeners.OnMovieClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private List<Movie> movies;
    private final OnMovieClickListener onMovieClickListener;


    public MoviesAdapter(final OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
        movies = new ArrayList<>();
    }


    @Override
    public MoviesAdapter.MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        final Movie movie = movies.get(position);
        if (movie != null) {
            GlideApp.with(holder.itemView.getContext())
                    .load(movie.getCoverUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.color_placeholder)
                    .into(holder.cover);

            holder.title.setText(movie.getTitle());
            holder.releaseDate.setText(movie.getReleaseDate());
            holder.description.setText(movie.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    private Movie getMovie(int position) {
        return movies.get(position);
    }

    public void setMovies(@NonNull List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public List<Movie> setFilter(final String filterText) {
        final List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(filterText.toLowerCase())) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public final class MovieHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_cover)
        ImageView cover;
        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.movie_release_date)
        TextView releaseDate;
        @BindView(R.id.movie_desc)
        TextView description;


        @OnClick(R.id.movie_item)
        public void onClick() {
            final int position = getLayoutPosition();
            onMovieClickListener.onMovieClick(getMovie(position));
        }

        MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}




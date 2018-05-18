package com.myst3ry.weekmovies.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.WeekMoviesApp;
import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.model.MovieDao;
import com.myst3ry.weekmovies.network.MoviesApiMock;
import com.myst3ry.weekmovies.ui.fragment.ActorFragment;
import com.myst3ry.weekmovies.ui.fragment.MovieDetailFragment;
import com.myst3ry.weekmovies.ui.fragment.WatchlistFragment;
import com.myst3ry.weekmovies.ui.fragment.WeekMoviesFragment;
import com.myst3ry.weekmovies.ui.listeners.OnActorClickListener;
import com.myst3ry.weekmovies.ui.listeners.OnMovieClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity implements OnMovieClickListener, OnActorClickListener {

    @Inject
    MoviesApiMock apiMock;
    @Inject
    MovieDao movieDao;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view_main)
    NavigationView navigationView;
    @BindView(R.id.drawer_main)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        WeekMoviesApp.getDataComponent(this).inject(this);

        setSupportActionBar(toolbar);
        setupDrawer();

        if (savedInstanceState == null) {
            final List<Movie> movies = apiMock.getMovies(); //get it with rx
            movieDao.insertOrReplaceInTx(movies);
            initUI();
        }
    }

    private void initUI() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, new WeekMoviesFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int id = item.getItemId();

                switch (id) {
                    case R.id.nav_item_new_week_movies:
                        switchContent(new WeekMoviesFragment());
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.nav_item_watchlist:
                        switchContent(new WatchlistFragment());
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void switchContent(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onMovieClick(@NonNull final Movie movie) {
        final Fragment fragment = MovieDetailFragment.newInstance(movie);
        switchContent(fragment);
    }

    @Override
    public void onActorClick(@NonNull final Actor actor) {
        final Fragment fragment = ActorFragment.newInstance(actor);
        switchContent(fragment);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

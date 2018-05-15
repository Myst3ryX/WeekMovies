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
import com.myst3ry.weekmovies.listeners.OnActorClickListener;
import com.myst3ry.weekmovies.listeners.OnMovieClickListener;
import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.ui.fragment.ActorFragment;
import com.myst3ry.weekmovies.ui.fragment.MovieDetailFragment;
import com.myst3ry.weekmovies.ui.fragment.WatchlistFragment;
import com.myst3ry.weekmovies.ui.fragment.WeekMoviesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity implements OnMovieClickListener, OnActorClickListener {

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

        setSupportActionBar(toolbar);
        setupDrawer();

        if (savedInstanceState == null) {
            initUI();
        }

    }

    private void initUI() {
        switchContent(new WeekMoviesFragment());
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
        Fragment fragment = MovieDetailFragment.newInstance(movie);
        switchContent(fragment);
    }

    @Override
    public void onActorClick(@NonNull final Actor actor) {
        Fragment fragment = ActorFragment.newInstance(actor);
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

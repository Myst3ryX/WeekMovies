package com.myst3ry.weekmovies.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.listeners.OnMovieClickListener;
import com.myst3ry.weekmovies.model.Movie;
import com.myst3ry.weekmovies.ui.fragment.MovieDetailFragment;
import com.myst3ry.weekmovies.ui.fragment.WatchlistFragment;
import com.myst3ry.weekmovies.ui.fragment.WeekMoviesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity implements OnMovieClickListener {

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
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, new WeekMoviesFragment(), "week_movies")
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
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, new WeekMoviesFragment(), "week_movies")
                                .addToBackStack(WeekMoviesFragment.class.getSimpleName())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.nav_item_watchlist:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, new WatchlistFragment(), "watchlist")
                                .addToBackStack(WatchlistFragment.class.getSimpleName())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onMovieClick(@NonNull final Movie movie, int position) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new MovieDetailFragment(), "movie_detail")
                .addToBackStack(MovieDetailFragment.class.getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

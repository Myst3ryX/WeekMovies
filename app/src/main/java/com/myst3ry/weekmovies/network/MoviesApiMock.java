package com.myst3ry.weekmovies.network;

import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public final class MoviesApiMock {

    private final List<Movie> movies;
    private final List<Actor> actors;

    public MoviesApiMock() {
        movies = new ArrayList<>();
        actors = new ArrayList<>();
        fillMovies();
    }

    private void fillMovies() {
        for (int i = 0; i < 20; i++) {
            Movie movie = new Movie();
            movie.setCoverUrl("");
            movie.setTitle("Star Wars");
            movie.setReleaseDate("1976");
            movie.setDescription("In far far galaxy...");

            for (int j = 0; j < 10; j++) {
                Actor actor = new Actor();
                actor.setPhotoUrl("");
                actor.setName("Mark Hamill");
                actor.setBirthdayDate("05.12.1951");
                actor.setBiography("Mark Hamill was born in 1951...");

                List<Movie> actorMovies = new ArrayList<>();
                actorMovies.add(movie);
                actorMovies.add(movie);
                actorMovies.add(movie);
                actorMovies.add(movie);
                actor.setMovies(actorMovies);

                actors.add(actor);
            }

            movie.setActors(actors);
            movies.add(movie);
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }
}

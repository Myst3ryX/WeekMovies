package com.myst3ry.weekmovies.network;

import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public final class MoviesApiMock {

    private final List<Movie> movies;

    public MoviesApiMock() {
        movies = new ArrayList<>();
        fillMoviesList();
        addMoviesListToActors();
    }

    private void fillMoviesList() {
        movies.add(new Movie(1L, "", "Darkest Hour", "2017",
                "In May 1940, the fate of Western Europe hangs on British Prime Minister Winston Churchill," +
                        " who must decide whether to negotiate with Adolf Hitler," +
                        " or fight on knowing that it could mean a humiliating defeat for Britain and its empire.", fillActorsList(1)));
        movies.add(new Movie(2L, "", "The Fifth Element", "1997", "In the colorful future," +
                " a cab driver unwittingly becomes the central figure in the search for a legendary cosmic weapon" +
                " to keep Evil and Mr. Zorg at bay.", fillActorsList(2)));
        movies.add(new Movie(3L, "", "Die Hard", "1988", "John McClane, officer of the NYPD, tries" +
                " to save his wife Holly Gennaro and several others that were taken hostage by German terrorist Hans Gruber" +
                " during a Christmas party at the Nakatomi Plaza in Los Angeles.", fillActorsList(1)));
        movies.add(new Movie(4L, "", "Wall Street", "1987", "Movie description is empty", fillActorsList(6)));
        movies.add(new Movie(5L, "", "Home Alone", "1990", "Movie description is empty", fillActorsList(3)));
        movies.add(new Movie(6L, "", "Crazy Heart", "2009", "Movie description is empty", fillActorsList(4)));
        movies.add(new Movie(7L, "", "Crash", "2004", "Movie description is empty", fillActorsList(3)));
        movies.add(new Movie(8L, "", "Snake Eyes", "1998", "Movie description is empty", fillActorsList(5)));
        movies.add(new Movie(9L, "", "From Dusk Till Dawn", "1996", "Movie description is empty", fillActorsList(4)));
    }

    private List<Actor> fillActorsList(final int cast) {
        final List<Actor> actors = new ArrayList<>();

        if (cast == 1) {
            actors.add(new Actor(1L, "", "Gary Oldman", "Born: March 21, 1958", "Gary Oldman is" +
                    " a talented English movie star and character actor, renowned for his \"big\" acting style." +
                    " One of the most celebrated thespians of his generation, with a diverse career encompassing theatre," +
                    " film and television, he is known for his roles as Sid Vicious in Сид и Нэнси (1986), Drexl ...", null));
        } else if (cast == 2) {
            actors.add(new Actor(2L, "", "Bruce Willis", "Born: March 19, 1955", "Actor and musician Bruce Willis is well known" +
                    " for playing wisecracking or hard-edged characters, often in spectacular action films. Collectively," +
                    " he has appeared in films that have grossed in excess of $2.5 billion USD, placing him in the top" +
                    " ten stars in terms of box office receipts. Walter Bruce Willis was born on March 19, 1955, in ...", null));
            actors.add(new Actor(1L, "", "Gary Oldman", "Born: March 21, 1958", "Gary Oldman is" +
                    " a talented English movie star and character actor, renowned for his \"big\" acting style." +
                    " One of the most celebrated thespians of his generation, with a diverse career encompassing theatre," +
                    " film and television, he is known for his roles as Sid Vicious in Сид и Нэнси (1986), Drexl ...", null));
        } else if (cast == 3) {
            actors.add(new Actor(3L, "", "Mark Sheppard", "Born: March 17, 1988", "Actor biography is empty", null));
            actors.add(new Actor(4L, "", "Guy Hamilton", "Born: Jule 13, 1975", "Actor biography is empty", null));
            actors.add(new Actor(5L, "", "Nash Olsen", "Born: March 11, 1958", "Actor biography is empty", null));
        } else {
            actors.add(new Actor(6L, "", "Lily Taylor", "Born: March 07, 1966", "Actor biography is empty", null));
            actors.add(new Actor(7L, "", "John Malkovich", "Born: June 22, 1971", "Actor biography is empty", null));
            actors.add(new Actor(8L, "", "Robert Oldman", "Born: March 05, 1948", "Actor biography is empty", null));
            actors.add(new Actor(9L, "", "Jim Jordan", "Born: April 11, 1885", "Actor biography is empty", null));
            actors.add(new Actor(10L, "", "Clark Kent", "Born: October 05, 1755", "Actor biography is empty", null));
        }

        return actors;
    }

    private void addMoviesListToActors() {
        for (int i = 0; i < movies.size(); i++) {
            final List<Actor> actors = movies.get(i).getActors();
            for (Actor actor : actors) {
                final List<Movie> actorMovies = new ArrayList<>();
                actorMovies.add(movies.get(i));
                if (i < movies.size() - 1) {
                    actorMovies.add(movies.get(i + 1));
                }
                actor.setMovies(actorMovies);
            }
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }
}

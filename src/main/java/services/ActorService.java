package services;

import domain.Actor;
import domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class ActorService {
    private static List<Actor> db = new ArrayList<Actor>();
    private MovieService movieDB = new MovieService();

    private static  int currentActorId = 0;

    public void add(Actor a ){
        a.setId(++currentActorId);
        db.add(a);
    }

    public Actor get(int id ){
        for (Actor a: db) {
            if(a.getId()==id){
                return a;
            }
        }
        return null;
    }

    public List<Movie> getMovies(int id ){
        Actor actor = get(id);
        if (actor == null){
            return null;
        }

        List<Integer> moviesIdList = actor.getMoviesList();
        List<Movie> movies = new ArrayList<>();
        if (moviesIdList != null){
            for (Integer currentId: moviesIdList) {
                Movie movie = movieDB.get(currentId);
                if (movie != null){
                    movies.add(movie);
                }

            }
        }
        return movies;
    }


}

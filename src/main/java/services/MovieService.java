package services;

import domain.Actor;
import domain.Movie;
import domain.MovieComment;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private static List<Movie> db = new ArrayList<Movie>();
    private static int currentId = 1;
    private static int commentId = 1;



    public  List<Movie> getAll(){
        return db;
    }

    public void addMovie(Movie movie){
        movie.setId(currentId++);
        db.add(movie);
    }


    public Movie get(int id){
        for(Movie movie : db){
            if(movie.getId()==id)
                return movie;
        }
        return null;
    }



    public void update(Movie movie){
        for(Movie m : db){
            if(m.getId() == movie.getId()){
                m.setTitle(movie.getTitle());
                m.setAboutMovie(movie.getAboutMovie());
            }
        }
    }

    public void delete(Movie movie){
        db.remove(movie);
    }


    public void addMovieComment(MovieComment comment, Movie movie){
        comment.setId(commentId++);
        movie.getComments().add(comment);
    }

    public void deleteComment(int id, Movie movie){
        for(MovieComment comment : movie.getComments()){
            if(comment.getId()==id){
                movie.getComments().remove(comment);
            }
        }
    }

    public void updateRate(Movie movie, double rating){
        double newRating = (movie.getRating() * movie.getCountRating() + rating) / (movie.getCountRating() + 1);
        movie.setCountRating(movie.getCountRating() + 1);
        movie.setRating(newRating);
    }

//    public void addActor (Actor actor, Movie movie){
//        movie.getActors().add(actor);
//    }



}

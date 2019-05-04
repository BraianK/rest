package domain;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String aboutMovie;
    private List<MovieComment> comments;
    private List<Actor> actors;
    private double rating;
    private int countRating;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAboutMovie() {
        return aboutMovie;
    }

    public void setAboutMovie(String aboutMovie) {
        this.aboutMovie = aboutMovie;
    }

    public List<MovieComment> getComments() {
        return comments;
    }

    public void setComments(List<MovieComment> comments) {
        this.comments = comments;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCountRating() {
        return countRating;
    }

    public void setCountRating(int countRating) {
        this.countRating = countRating;
    }



}
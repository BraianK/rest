package resources;

import domain.Actor;
import domain.Movie;
import domain.MovieComment;
import services.MovieService;
//import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path("/movie")
public class MovieResources {
    private MovieService db = new MovieService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll(){
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie){
        db.addMovie(movie);
        return Response.ok(movie.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id){
        Movie result = db.get(id);
        if(result==null){
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Movie movie){
        Movie result = db.get(id);
        if(result==null) {
            return Response.status(404).build();
        }
        movie.setId(id);
        db.update(movie);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        Movie result = db.get(id);
        if(result==null){
            return Response.status(404).build();
        }
        db.delete(result);
        return Response.ok().build();
    }


//dodawanie komentarza do filmu
    @POST
    @Path("/{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("id") int id, MovieComment comment){
        Movie result = db.get(id);
        if(result==null){
            return Response.status(404).build();
        }
        if(result.getComments()==null){
            result.setComments(new ArrayList<MovieComment>());
        }
        db.addMovieComment(comment,result);
        return Response.ok().build();
    }



    //Lista komentarzy danego filmu
    @GET
    @Path("/{movieId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieComment> getComment(@PathParam("movieId") int movieId) {
        Movie result = db.get(movieId);
        if (result == null) {
            return null;
        }
        if (result.getComments() == null) {
            result.setComments(new ArrayList<MovieComment>());
        }
        return result.getComments();
    }

    @DELETE
    @Path("/{movieId}/comments/{commentId}")
    public Response deleteComment(@PathParam("movieId") int movieId,@PathParam("commentId") int commentId){
        Movie movie = db.get(movieId);
        if(movie==null){
            return Response.status(404).build();
        }
        if(movie.getComments() == null){
            return Response.status(404).build();
        }
        db.deleteComment(commentId, movie);
        return Response.ok().build();
    }



    @PUT
    @Path("/{movieId}/rating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRate(@PathParam("movieId") int movieId, Movie rating){
        Movie movie = db.get(movieId);
        if(movie == null){
            return Response.status(404).build();
        }

        double rate = rating.getRating();

        db.updateRate(movie,rate);
        return Response.ok().build();
    }







    @GET
    @Path("/{movieId}/actors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getActors(@PathParam("movieId") int movieId){
        Movie result = db.get(movieId);
        if (result == null){
            return null;
        }
        if (result.getActors() == null){
            result.setActors(new ArrayList<Actor>());
        }
        return result.getActors();
    }


//Lista aktorów danego filmu
//    @GET
//    @Path("/{movieId}/actor")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Actor> getActor(@PathParam("movieId") int movieId) {
//        Movie result = db.get(movieId);
//        if (result == null) {
//            return null;
//        }
//        if (result.getActors() == null) {
//            result.setActors(new ArrayList<Actor>());
//        }
//            return result.getActors();
//        }
//dodawanie aktorów do filmu
//    @POST
//    @Path("/{id}/actor")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addActor(@PathParam("id") int movieId, Actor actor){
//        Movie result = db.get(movieId);
//        if(result==null){
//            return Response.status(404).build();
//        }
//        if(result.getActors()==null){
//            result.setActors(new ArrayList<Actor>());
//         }
//        result.getActors().add(actor);
//        return Response.ok().build();
//    }


}

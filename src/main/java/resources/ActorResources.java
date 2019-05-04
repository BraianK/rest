package resources;


import domain.Actor;
import domain.Movie;
import services.ActorService;
import services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/actor")
public class ActorResources {
    private ActorService actorDB = new ActorService();
    private MovieService movieDB = new MovieService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Actor actor){
        actorDB.add(actor);
        return Response.ok(actor.getId()).build();
    }

    @POST
    @Path("/{actorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActorToMovie(@PathParam("actorId") int actorId, int movieId){
        Actor actor = actorDB.get(actorId);
        Movie movie = movieDB.get(movieId);
        if (actor == null || movie == null){
            return Response.status(404).build();
        }
        List<Integer> movieList =  actor.getMoviesList();
        if (movieList == null){
            movieList = new ArrayList<Integer>();
        }
        movieList.add(movieId);
        actor.setMoviesList(movieList);

        List<Actor> actorList =  movie.getActors();
        if (actorList == null){
            actorList = new ArrayList<Actor>();
        }
        actorList.add(actor);
        movie.setActors(actorList);
        return Response.ok().build();
    }

    @GET
    @Path("/{actorId}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorMovies(@PathParam("actorId") int actorId){
        List<Movie> movies =  actorDB.getMovies(actorId);
        if (movies == null){
            return Response.status(404).build();
        }
        return  Response.ok(movies).build();

    }

}

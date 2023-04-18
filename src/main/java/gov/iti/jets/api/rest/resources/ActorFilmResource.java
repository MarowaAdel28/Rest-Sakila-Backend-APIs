package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.service.ActorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("actor_films")
public class ActorFilmResource {

    private ActorService actorService;
    public ActorFilmResource() {
        actorService = ActorService.getInstance();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FilmDto> gatAllFilms(@PathParam("id") Short id) {
        return actorService.getActorFilmList(id);
    }

    @GET
    @Path("filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FilmDto> getFilmListByRating(@QueryParam("actorId") Short id, @QueryParam("rating") String rate,
                                             @QueryParam("language") String language) {
        if(rate!=null) {
            return actorService.getActorFilmListByRating(id,rate);
        }
        if(language!=null) {
            return actorService.getActorFilmListByLanguage(id,language);
        }
        return null;
    }

    // sumation

}

package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.service.ActorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
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
    public Response gatAllFilms(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        List<FilmDto> filmDtoList = actorService.getActorFilmList(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actor_films").path("filter")
                        .queryParam("actorId",id).queryParam("rating","PG"))
                .rel("filter by rating").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actor_films").path("filter")
                        .queryParam("actorId",id).queryParam("language","English"))
                .rel("filter by language").build();
        links.add(link);

        return Response.ok(filmDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmListByRating(@QueryParam("actorId") Short id, @QueryParam("rating") String rate,
                                             @QueryParam("language") String language, @Context UriInfo uriInfo) {
        List<FilmDto> filmDtoList = null;
        if(rate!=null) {
            filmDtoList = actorService.getActorFilmListByRating(id,rate);
        }
        if(language!=null) {
            filmDtoList = actorService.getActorFilmListByLanguage(id,language);
        }

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actor_films").path(id.toString())).rel("actor films").build();
        links.add(link);

        return Response.ok(filmDtoList).links(links.toArray(new Link[0])).build();
    }

    // sumation
}

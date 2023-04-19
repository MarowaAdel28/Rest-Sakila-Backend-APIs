package gov.iti.jets.api.rest.resources;

import gov.iti.jets.entity.FilmText;
import gov.iti.jets.service.FilmService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("films_text")
public class FilmTextResource {

    private FilmService filmService;
    public FilmTextResource() {
        filmService = FilmService.getFilmServiceInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<FilmText> filmTextList = filmService.getAllFilmText();
        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("films_text").path("1"))
                .rel("films_text").build();
        links.add(link);

        return Response.ok(filmTextList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        FilmText filmText = filmService.getFilmText(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("films_text"))
                .rel("films_text").build();
        links.add(link);

        return Response.ok(filmText).links(links.toArray(new Link[0])).build();
    }
}

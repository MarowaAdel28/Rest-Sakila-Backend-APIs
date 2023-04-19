package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.dto.FilmFormDto;
import gov.iti.jets.service.FilmService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("films")
public class FilmResource {

    FilmService filmService;

    public FilmResource() {
        filmService = FilmService.getFilmServiceInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<FilmDto> filmDtoList = filmService.getAll();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("films").path("1"))
                .rel("film").build();
        links.add(link);


        return Response.ok(filmDtoList).links(links.toArray(new Link[0])).build();
    }
   @GET
   @Path("{id:[0-9]+}")
   @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        FilmDto filmDto = filmService.getById(id);

       List<Link> links = new ArrayList<>();

       Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
       links.add(link);

       link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("films"))
               .rel("films").build();
       links.add(link);


       return Response.ok(filmDto).links(links.toArray(new Link[0])).build();
   }

   @POST
   @Consumes({"application/xml", "application/json"})
    public boolean addFilm(FilmFormDto filmFormDto) {
        return filmService.addFilm(filmFormDto);
   }
}

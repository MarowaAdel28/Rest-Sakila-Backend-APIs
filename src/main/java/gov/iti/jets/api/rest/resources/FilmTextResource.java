package gov.iti.jets.api.rest.resources;

import gov.iti.jets.entity.FilmText;
import gov.iti.jets.service.FilmService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("films_text")
public class FilmTextResource {

    private FilmService filmService;
    public FilmTextResource() {
        filmService = FilmService.getFilmServiceInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FilmText> getAll() {
        return filmService.getAllFilmText();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public FilmText getById(@PathParam("id") Short id) {
        return filmService.getFilmText(id);
    }
}

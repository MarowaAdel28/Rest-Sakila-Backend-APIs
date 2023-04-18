package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.dto.FilmFormDto;
import gov.iti.jets.service.FilmService;
import gov.iti.jets.views.NicerButSlowerFilmList;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("films")
public class FilmResource {

    FilmService filmService;

    public FilmResource() {
        filmService = FilmService.getFilmServiceInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FilmDto> getAll() {
        return filmService.getAll();
    }
   @GET
   @Path("{id:[0-9]+}")
   @Produces(MediaType.APPLICATION_JSON)
    public FilmDto getById(@PathParam("id") Short id) {
        return filmService.getById(id);
   }

   @POST
   @Consumes({"application/xml", "application/json"})
    public boolean addFilm(FilmFormDto filmFormDto) {
        return filmService.addFilm(filmFormDto);
   }
}

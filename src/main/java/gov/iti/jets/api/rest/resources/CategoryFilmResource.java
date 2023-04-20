package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.service.CategoryService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("category_films")
public class CategoryFilmResource {

    private CategoryService categoryService;

    public CategoryFilmResource() {
        categoryService = CategoryService.getInstance();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryFilms(@PathParam("id") short id, @Context UriInfo uriInfo) {

        List<FilmDto> filmDtoList = categoryService.getCategoryFilms(id);
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.ok(filmDtoList).links(self).build();
    }

    // count
}

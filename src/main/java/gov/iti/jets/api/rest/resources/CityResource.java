package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.CityDto;
import gov.iti.jets.service.CityService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("cities")
public class
CityResource {

    private CityService cityService;

    public CityResource() {
        cityService = CityService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<CityDto> cityDtoList = cityService.getAllCities();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("cities").path("1"))
                .rel("city").build();
        links.add(link);

        return Response.ok(cityDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") short id, @Context UriInfo uriInfo) {
        CityDto cityDto = cityService.getCityById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("cities"))
                .rel("cities").build();
        links.add(link);

        return Response.ok(cityDto).links(links.toArray(new Link[0])).build();
    }
}

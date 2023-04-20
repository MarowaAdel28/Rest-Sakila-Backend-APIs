package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.CityDto;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.service.CountryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("countries")
public class CountryResource {

    private CountryService countryService;

    public CountryResource() {
        countryService = CountryService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<CountryDto> countryDtoList = countryService.getAllCountries();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("countries").path("1"))
                .rel("country").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("countries").path("cities")
                        .queryParam("countryId",1))
                .rel("cities in country").build();
        links.add(link);

        return Response.ok(countryDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") short id, @Context UriInfo uriInfo) {
        CountryDto countryDto = countryService.getCountryById(id);
        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("countries"))
                .rel("countries").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("countries").path("cities")
                        .queryParam("countryId",id))
                .rel("cities in country").build();
        links.add(link);

        return Response.ok(countryDto).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("cities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryCityList(@QueryParam("countryId") short id, @Context UriInfo uriInfo) {
        List<CityDto> cityDtoList = countryService.getCountryCityList(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("countries"))
                .rel("countries").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("countries").path("1"))
                .rel("country").build();
        links.add(link);

        return Response.ok(cityDtoList).links(links.toArray(new Link[0])).build();
    }
}

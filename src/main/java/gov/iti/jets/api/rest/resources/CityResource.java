package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.CityDto;
import gov.iti.jets.service.CityService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
    public List<CityDto> getAll() {
        return cityService.getAllCities();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public CityDto getById(@PathParam("id") short id) {
        return cityService.getCityById(id);
    }
}

package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.CityDto;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.service.CountryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("countries")
public class CountryResource {

    private CountryService countryService;

    public CountryResource() {
        countryService = CountryService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountryDto> getAll() {
        return countryService.getAllCountries();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public CountryDto getById(@PathParam("id") short id) {
        return countryService.getCountryById(id);
    }

    @GET
    @Path("cities")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CityDto> getCountryCityList(@QueryParam("countryId") short id) {
        return countryService.getCountryCityList(id);
    }
}

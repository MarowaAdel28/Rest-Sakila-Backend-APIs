package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.LanguageDto;
import gov.iti.jets.dto.LanguageFormDto;
import gov.iti.jets.service.LanguageService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("languages")
public class LanguageResource {

    private LanguageService languageService;

    public LanguageResource() {
        languageService = LanguageService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LanguageDto> getAll() {
        return languageService.getAll();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public LanguageDto getById(@PathParam("id") Short id) {
        return languageService.getById(id);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public boolean addLanguage(LanguageFormDto languageFormDto) {
        return languageService.addLanguage(languageFormDto.getName());
    }

    @PUT
    @Path("{id:[0-9]+}")
    @Consumes({"application/xml", "application/json"})
    public boolean editLanguage(@PathParam("id") Short id, LanguageFormDto languageFormDto) {
        return languageService.editLanguage(id,languageFormDto.getName());
    }
}

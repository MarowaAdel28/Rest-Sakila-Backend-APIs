package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.LanguageDto;
import gov.iti.jets.dto.LanguageFormDto;
import gov.iti.jets.service.LanguageService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("languages")
public class LanguageResource {

    private LanguageService languageService;

    public LanguageResource() {
        languageService = LanguageService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<LanguageDto> languageDtoList = languageService.getAll();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("languages").path("1"))
                .rel("language").build();
        links.add(link);

        return Response.ok(languageDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        LanguageDto languageDto = languageService.getById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories"))
                .rel("inventories").build();
        links.add(link);

        return Response.ok(languageDto).links(links.toArray(new Link[0])).build();
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

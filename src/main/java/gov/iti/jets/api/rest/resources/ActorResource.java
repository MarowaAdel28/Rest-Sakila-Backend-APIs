package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.dto.ActorFormDto;
import gov.iti.jets.service.ActorService;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("actors")
public class ActorResource {

    private ActorService actorService;

    public ActorResource() {
        actorService = ActorService.getInstance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors(@Context UriInfo uriInfo) {
        List<ActorDto> actorDtoList = actorService.getActorList();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actors").path("1"))
                .rel("actor").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actors").path("search")
                        .queryParam("name","Ahmed"))
                .rel("search").build();
        links.add(link);

        return Response.ok(actorDtoList).links(links.toArray(new Link[0])).build();
    }

//    @GET
//    @Path("{id: [0-9]+}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ActorDto getActorById(@PathParam("id") Short id) {
//        return actorService.getActorById(id);
//    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        ActorDto actorDto = actorService.getActorById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actors"))
                .rel("actors").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actors").path("search")
                        .queryParam("name","Ahmed"))
                .rel("search").build();
        links.add(link);

        return Response.ok(actorDto).links(links.toArray(new Link[0])).build();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public boolean addActor(ActorFormDto actorDto) {
        return actorService.addActor(actorDto);
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("ActorName") String name, @Context UriInfo uriInfo) {
        List<ActorDto> actorDtoList = actorService.searchActorByName(name);
        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actors").path("1"))
                .rel("actor").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("actors"))
                .rel("actors").build();
        links.add(link);

        return Response.ok(actorDtoList).links(links.toArray(new Link[0])).build();
    }

    @Path("{id: [0-9]+}")
    @PUT
    @Consumes({"application/xml", "application/json"})
//    @Consumes(MediaType.APPLICATION_JSON)
    public boolean editActor(@PathParam("id") Short id, ActorFormDto actorDto) {
        return actorService.editActor(id,actorDto);
    }

}

// /actors
// /actors/id

// /actors post (add)
// /actors/id put,delete

// ActorFormDto
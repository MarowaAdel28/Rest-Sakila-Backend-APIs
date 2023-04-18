package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.dto.ActorFormDto;
import gov.iti.jets.service.ActorService;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("actors")
public class ActorResource {

    private ActorService actorService;

    public ActorResource() {
        actorService = ActorService.getInstance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActorDto> getAllActors() {
        return actorService.getActorList();
    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public ActorDto getActorById(@PathParam("id") Short id) {
        return actorService.getActorById(id);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public boolean addActor(ActorFormDto actorDto) {
        return actorService.addActor(actorDto);
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActorDto> search(@QueryParam("ActorName") String name) {
        return actorService.searchActorByName(name);
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
package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.StaffDto;
import gov.iti.jets.dto.StaffFormDto;
import gov.iti.jets.service.StaffService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("staffs")
public class StaffResource {

    private StaffService staffService;

    public StaffResource() {
        staffService = StaffService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<StaffDto> staffDtoList = staffService.getAll();
        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("staffs").path("1"))
                .rel("staff").build();
        links.add(link);

        return Response.ok(staffDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}")
    public Response getById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        StaffDto staffDto = staffService.getById(id);
        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("staffs"))
                .rel("staffs").build();
        links.add(link);

        return Response.ok(staffDto).links(links.toArray(new Link[0])).build();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public boolean addStaff(StaffFormDto staffFormDto) {
        return staffService.addStaff(staffFormDto);
    }

    @PUT
    @Path("{id:[0-9]+}")
    @Consumes({"application/xml", "application/json"})
    public boolean editStaff(@PathParam("id") Short id, StaffFormDto staffFormDto) {
        return staffService.editStaff(id,staffFormDto);
    }

    @DELETE
    @Path("{id:[0-9]+}")
    public Response deleteStaff(@PathParam("id") Short id) {
        boolean result = staffService.deleteStaff(id);
        if(result) {
            return Response.ok("deleted successfully").build();
        }
        return Response.ok("failed to delete it").build();
    }
}

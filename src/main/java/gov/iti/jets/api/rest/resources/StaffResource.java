package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.StaffDto;
import gov.iti.jets.dto.StaffFormDto;
import gov.iti.jets.service.StaffService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("staffs")
public class StaffResource {

    private StaffService staffService;

    public StaffResource() {
        staffService = StaffService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StaffDto> getAll() {
        return staffService.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}")
    public StaffDto getById(@PathParam("id") Short id) {
        return staffService.getById(id);
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
}

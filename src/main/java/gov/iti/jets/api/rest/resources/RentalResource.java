package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.dto.RentalDto;
import gov.iti.jets.dto.RentalFormDto;
import gov.iti.jets.service.RentalService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("rentals")
public class RentalResource {

    private RentalService rentalService;

    public RentalResource() {
        rentalService = RentalService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RentalDto> getAll() {
        return rentalService.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}")
    public RentalDto getById(@PathParam("id") Short id) {
        return rentalService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}/payment")
    public List<PaymentDto> getPaymentList(@PathParam("id") Short id) {
        return rentalService.getPaymentList(id);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public boolean addRental(RentalFormDto rentalFormDto) {
        return rentalService.addRental(rentalFormDto);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    @Path("{id:[0-9]+}")
    public boolean editRental(@PathParam("id") Integer id, RentalFormDto rentalFormDto) {
        return rentalService.editRental(id,rentalFormDto);
    }
}

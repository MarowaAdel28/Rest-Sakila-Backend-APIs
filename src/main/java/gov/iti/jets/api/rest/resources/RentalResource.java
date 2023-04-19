package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.dto.RentalDto;
import gov.iti.jets.dto.RentalFormDto;
import gov.iti.jets.service.RentalService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("rentals")
public class RentalResource {

    private RentalService rentalService;

    public RentalResource() {
        rentalService = RentalService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<RentalDto> rentalDtoList = rentalService.getAll();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("rentals").path("1"))
                .rel("rental").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("rentals").path("1").path("payment"))
                .rel("payment list of rental").build();
        links.add(link);

        return Response.ok(rentalDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}")
    public Response getById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        RentalDto rentalDto = rentalService.getById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("rentals"))
                .rel("rentals").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("rentals").path("1").path("payment"))
                .rel("payment list of rental").build();
        links.add(link);

        return Response.ok(rentalDto).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}/payment")
    public Response getPaymentList(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        List<PaymentDto> paymentDtoList = rentalService.getPaymentList(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("rentals").path("1"))
                .rel("rental").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("rentals"))
                .rel("rentals").build();
        links.add(link);

        return Response.ok(paymentDtoList).links(links.toArray(new Link[0])).build();
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

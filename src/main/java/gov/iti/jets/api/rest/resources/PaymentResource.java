package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.service.PaymentService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("payments")
public class PaymentResource {

    private PaymentService paymentService;

    public PaymentResource() {
        paymentService = PaymentService.getInstance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<PaymentDto> paymentDtoList = paymentService.getAll();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("payments").path("1"))
                .rel("payment").build();
        links.add(link);

        return Response.ok(paymentDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") short id, @Context UriInfo uriInfo) {
        PaymentDto paymentDto = paymentService.getById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("payments"))
                .rel("payments").build();
        links.add(link);

        return Response.ok(paymentDto).links(links.toArray(new Link[0])).build();
    }
}

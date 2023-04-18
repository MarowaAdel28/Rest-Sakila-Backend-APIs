package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.service.PaymentService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("payments")
public class PaymentResource {

    private PaymentService paymentService;

    public PaymentResource() {
        paymentService = PaymentService.getInstance();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaymentDto> getAll() {
        return paymentService.getAll();
    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public PaymentDto getById(@PathParam("id") short id) {
        return paymentService.getById(id);
    }
}

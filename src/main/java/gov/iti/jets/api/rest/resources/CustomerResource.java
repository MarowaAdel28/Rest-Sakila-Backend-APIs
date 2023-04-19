package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.CustomerFormDto;
import gov.iti.jets.dto.CustomerInfoDto;
import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.dto.RentalDto;
import gov.iti.jets.service.CustomerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("customers")
public class CustomerResource {

    private CustomerService customerService;

    public CustomerResource() {
        customerService = CustomerService.getCustomerService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<CustomerInfoDto> customerInfoDtoList = customerService.getAllCustomers();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1"))
                .rel("customer").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("filter")
                        .queryParam("isActive",true))
                .rel("filter by customer activation").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("payment"))
                .rel("customer payment").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("rental"))
                .rel("customer rental").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("search").queryParam("name","marwa"))
                .rel("search").build();
        links.add(link);

        return Response.ok(customerInfoDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") short id, @Context UriInfo uriInfo) {
        CustomerInfoDto customerInfoDto = customerService.getCustomerById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers"))
                .rel("customers").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("filter")
                        .queryParam("isActive",true))
                .rel("filter by customer activation").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("payment"))
                .rel("customer payment").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("rental"))
                .rel("customer rental").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("search").queryParam("name","marwa"))
                .rel("search").build();
        links.add(link);

        return Response.ok(customerInfoDto).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCustomer(@QueryParam("isActive") boolean isActive,@Context UriInfo uriInfo) {

        List<CustomerInfoDto> customerInfoDtoList = null;

        if(isActive) {
            customerInfoDtoList = customerService.getAllActiveCustomers();
        }
        if(!isActive) {
            customerInfoDtoList = customerService.getAllInactiveCustomers();
        }
        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1"))
                .rel("customer").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers"))
                .rel("customers").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("payment"))
                .rel("customer payment").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("rental"))
                .rel("customer rental").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("search").queryParam("name","marwa"))
                .rel("search").build();
        links.add(link);

        return Response.ok(customerInfoDtoList).links(links.toArray(new Link[0])).build();
    }

    // count

    @GET
    @Path("{id:[0-9]+}/payment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerPayment(@PathParam("id") short customerId, @Context UriInfo uriInfo) {
        List<PaymentDto> paymentDtoList = customerService.getCustomerPayment(customerId);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers"))
                .rel("customers").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("filter")
                        .queryParam("isActive",true))
                .rel("filter by customer activation").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1"))
                .rel("customer").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("rental"))
                .rel("customer rental").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("search").queryParam("name","marwa"))
                .rel("search").build();
        links.add(link);

        return Response.ok(paymentDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}/rental")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerRental(@PathParam("id") short customerId, @Context UriInfo uriInfo) {
        List<RentalDto> rentalDtoList = customerService.getCustomerRental(customerId);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers"))
                .rel("customers").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("filter")
                        .queryParam("isActive",true))
                .rel("filter by customer activation").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("payment"))
                .rel("customer payment").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1"))
                .rel("customer").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("search").queryParam("name","marwa"))
                .rel("search").build();
        links.add(link);

        return Response.ok(rentalDtoList).links(links.toArray(new Link[0])).build();
    }

    // count

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@QueryParam("name") String name, @Context UriInfo uriInfo) {
        List<CustomerInfoDto> customerInfoDtoList = customerService.searchByName(name);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers"))
                .rel("customers").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("filter")
                        .queryParam("isActive",true))
                .rel("filter by customer activation").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("payment"))
                .rel("customer payment").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1").path("rental"))
                .rel("customer rental").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("customers").path("1"))
                .rel("customer").build();
        links.add(link);

        return Response.ok(customerInfoDtoList).links(links.toArray(new Link[0])).build();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public boolean addCustomer(CustomerFormDto customerFormDto) {
        return customerService.addCustomer(customerFormDto);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    @Path("{id:[0-9]+}")
    public boolean editCustomer(@PathParam("id") short id, CustomerFormDto customerFormDto) {
        return customerService.editCustomer(id,customerFormDto);
    }
}

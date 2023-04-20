package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.*;
import gov.iti.jets.service.InventoryService;
import gov.iti.jets.service.StoreService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("stores")
public class StoreResource {

    private StoreService storeService;

    private InventoryService inventoryService;
    public StoreResource() {
        storeService = StoreService.getInstance();
        inventoryService = InventoryService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<StoreDto> storeDtoList = storeService.getAllStores();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1"))
                .rel("store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("customers"))
                .rel("customer list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("staffs"))
                .rel("staff list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("inventories"))
                .rel("inventory list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("films"))
                .rel("film list of store").build();
        links.add(link);

        return Response.ok(storeDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        StoreDto storeDto = storeService.getStoreById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores"))
                .rel("stores").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("customers"))
                .rel("customer list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("staffs"))
                .rel("staff list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("inventories"))
                .rel("inventory list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("films"))
                .rel("film list of store").build();
        links.add(link);

        return Response.ok(storeDto).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreCustomerList(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        List<CustomerInfoDto> customerInfoDtoList = storeService.getStoreCustomerList(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1"))
                .rel("store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores"))
                .rel("stores").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("staffs"))
                .rel("staff list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("inventories"))
                .rel("inventory list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("films"))
                .rel("film list of store").build();
        links.add(link);

        return Response.ok(customerInfoDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}/staffs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreStaffList(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        List<StaffDto> staffDtoList = storeService.getStoreStaffList(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1"))
                .rel("store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("customers"))
                .rel("customer list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores"))
                .rel("stores").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("inventories"))
                .rel("inventory list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("films"))
                .rel("film list of store").build();
        links.add(link);

        return Response.ok(staffDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}/inventories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreInventoryList(@PathParam("id") Short storeId, @Context UriInfo uriInfo) {
        List<InventoryDto> inventoryDtoList = storeService.getStoreInventoryList(storeId);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1"))
                .rel("store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("customers"))
                .rel("customer list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("staffs"))
                .rel("staff list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores"))
                .rel("stores").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("films"))
                .rel("film list of store").build();
        links.add(link);

        return Response.ok(inventoryDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}/films")
    public Response getStoreFilms(@PathParam("id") Short storeId, @Context UriInfo uriInfo) {
        List<FilmDto> filmDtoList = inventoryService.getStoreFilms(storeId);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1"))
                .rel("store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("customers"))
                .rel("customer list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("staffs"))
                .rel("staff list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores").path("1").path("inventories"))
                .rel("inventory list of store").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("stores"))
                .rel("stores").build();
        links.add(link);

        return Response.ok(filmDtoList).links(links.toArray(new Link[0])).build();
    }

    @Consumes({"application/xml", "application/json"})
    @POST
    public boolean addStore(StoreFormDto storeFormDto) {
        return storeService.addStore(storeFormDto);
    }

    @Consumes({"application/xml", "application/json"})
    @PUT
    @Path("{id:[0-9]+}")
    public boolean editStore(@PathParam("id") Short id, StoreFormDto storeFormDto) {
        return storeService.editStore(id,storeFormDto);
    }

    @DELETE
    @Path("{id:[0-9]+}")
    public boolean deleteStore(@PathParam("id") Short id) {
        return storeService.deleteStore(id);
    }
}

package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.*;
import gov.iti.jets.service.InventoryService;
import gov.iti.jets.service.StoreService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
    public List<StoreDto> getAll() {
        return storeService.getAllStores();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public StoreDto getById(@PathParam("id") Short id) {
        return storeService.getStoreById(id);
    }

    @GET
    @Path("{id:[0-9]+}/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerInfoDto> getStoreCustomerList(@PathParam("id") Short id) {
        return storeService.getStoreCustomerList(id);
    }

    @GET
    @Path("{id:[0-9]+}/staffs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StaffDto> getStoreStaffList(@PathParam("id") Short id) {
        return storeService.getStoreStaffList(id);
    }

    @GET
    @Path("{id:[0-9]+}/inventries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryDto> getStoreInventoryList(@PathParam("id") Short storeId) {
        return storeService.getStoreInventoryList(storeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id:[0-9]+}/films")
    public List<FilmDto> getStoreFilms(@PathParam("id") Short storeId) {
        return inventoryService.getStoreFilms(storeId);
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
}

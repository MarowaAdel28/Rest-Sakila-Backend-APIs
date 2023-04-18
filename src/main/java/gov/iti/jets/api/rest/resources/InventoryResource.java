package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.InventoryDto;
import gov.iti.jets.service.InventoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("inventories")
public class InventoryResource {

    private InventoryService inventoryService;
    public InventoryResource() {
        inventoryService = InventoryService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryDto> getAll() {
        return inventoryService.getAll();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public InventoryDto getById(@PathParam("id") Short id) {
        return inventoryService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("filter")
    public List<InventoryDto> getByFilm(@QueryParam("filmId") Integer filmId, @QueryParam("storeId") Short storeId) {
        if (filmId != null) {
            return inventoryService.getByFilm(filmId);
        }
        if (storeId != null) {
            return inventoryService.getByStore(storeId);
        }
        return null;
    }
}

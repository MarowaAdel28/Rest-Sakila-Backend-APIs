package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.InventoryDto;
import gov.iti.jets.service.InventoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("inventories")
public class InventoryResource {

    private InventoryService inventoryService;
    public InventoryResource() {
        inventoryService = InventoryService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {
        List<InventoryDto> inventoryDtoList = inventoryService.getAll();

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories").path("1"))
                .rel("inventory").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories").path("filter")
                        .queryParam("filmId",1))
                .rel("filter by film").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories").path("filter")
                        .queryParam("storeId",1))
                .rel("filter by store").build();
        links.add(link);

        return Response.ok(inventoryDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Short id, @Context UriInfo uriInfo) {
        InventoryDto inventoryDto = inventoryService.getById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories"))
                .rel("inventories").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories").path("filter")
                        .queryParam("filmId",1))
                .rel("filter by film").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories").path("filter")
                        .queryParam("storeId",1))
                .rel("filter by store").build();
        links.add(link);

        return Response.ok(inventoryDto).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("filter")
    public Response getByFilm(@QueryParam("filmId") Integer filmId, @QueryParam("storeId") Short storeId,
                                        @Context UriInfo uriInfo) {
        List<InventoryDto> inventoryDtoList = null;
        if (filmId != null) {
            inventoryDtoList = inventoryService.getByFilm(filmId);
        }
        if (storeId != null) {
            inventoryDtoList = inventoryService.getByStore(storeId);
        }

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories").path("1"))
                .rel("inventory").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("inventories"))
                .rel("inventories").build();
        links.add(link);

        return Response.ok(inventoryDtoList).links(links.toArray(new Link[0])).build();
    }

    @DELETE
    @Path("{id:[0-9]+}")
    public boolean deleteInventory(@PathParam("id") Short id) {
        return inventoryService.deleteInventory(id);
    }
}

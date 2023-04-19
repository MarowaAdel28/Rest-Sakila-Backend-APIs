package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.CategoryFormDto;
import gov.iti.jets.service.CategoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

@Path("categories")
public class CategoryResource {

    private CategoryService categoryService;

    public CategoryResource() {
        categoryService = CategoryService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context UriInfo uriInfo) {

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("categories").path("1"))
                .rel("category").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("categories").path("search")
                        .queryParam("name","Classic"))
                .rel("search").build();
        links.add(link);

        return Response.ok(categoryDtoList).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") short id, @Context UriInfo uriInfo) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("categories"))
                .rel("categories").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("categories").path("search")
                        .queryParam("name","Classic"))
                .rel("search").build();
        links.add(link);

        return Response.ok(categoryDto).links(links.toArray(new Link[0])).build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@QueryParam("name") String name,@Context UriInfo uriInfo) {
        List<CategoryDto> categoryDtoList = categoryService.searchCategoryByName(name);

        List<Link> links = new ArrayList<>();

        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("categories").path("1"))
                .rel("category").build();
        links.add(link);

        link = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path("categories"))
                .rel("categories").build();
        links.add(link);

        return Response.ok(categoryDtoList).links(links.toArray(new Link[0])).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addCategory(CategoryFormDto categoryDto) {
        return categoryService.addCategory(categoryDto.getCategoryName());
    }

    @PUT
    @Path("{id: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean editCategory(@PathParam("id") Short categoryId, CategoryFormDto categoryDto) {
        return categoryService.editCategory(categoryId,categoryDto.getCategoryName());
    }

}

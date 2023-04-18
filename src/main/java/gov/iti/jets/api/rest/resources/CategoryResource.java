package gov.iti.jets.api.rest.resources;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.CategoryFormDto;
import gov.iti.jets.service.CategoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("categories")
public class CategoryResource {

    private CategoryService categoryService;

    public CategoryResource() {
        categoryService = CategoryService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDto> getAll() {
        return categoryService.getAllCategories();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDto getById(@PathParam("id") short id) {
        return categoryService.getCategoryById(id);
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDto> searchByName(@QueryParam("name") String name) {
        return categoryService.searchCategoryByName(name);
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

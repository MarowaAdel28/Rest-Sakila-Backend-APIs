package gov.iti.jets.service;

import gov.iti.jets.dao.CategoryDAO;
import gov.iti.jets.dao.DBFactory;
import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.entity.*;
import gov.iti.jets.mapper.ActorMapper;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.mapper.FilmActorMapper;
import gov.iti.jets.mapper.FilmMapper;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CategoryService {
    private volatile static CategoryService categoryService;

    private ActorMapper actorMapper;

    private FilmActorMapper filmActorMapper;

    private CategoryMapper categoryMapper;

    private FilmMapper filmMapper;
    public static CategoryService getInstance() {
        if (categoryService == null) {
            synchronized (ActorService.class) {
                if (categoryService == null) {
                    categoryService = new CategoryService();
                }
            }
        }
        return categoryService;
    }

    private CategoryService() {
        actorMapper = Mappers.getMapper(ActorMapper.class);
        filmActorMapper = Mappers.getMapper(FilmActorMapper.class);
        categoryMapper = Mappers.getMapper(CategoryMapper.class);
        filmMapper = Mappers.getMapper(FilmMapper.class);
    }

    public CategoryDto getCategoryById(short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        Category category = categoryDAO.get(id);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        dbFactory.closeEntityManager(entityManager);
        return categoryDto;
    }

    public List<CategoryDto> getAllCategories() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        List<Category> categories = categoryDAO.getAllCategories();
        List<CategoryDto> categoryDtoList = categoryMapper.toDTOs(categories);
        dbFactory.closeEntityManager(entityManager);
        return categoryDtoList;
    }

    public List<CategoryDto> searchCategoryByName(String name) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        List<Category> categories = categoryDAO.searchByCategoryName(name);
        List<CategoryDto> categoryDtoList = categoryMapper.toDTOs(categories);
        dbFactory.closeEntityManager(entityManager);
        return categoryDtoList;
    }

    public List<FilmDto> getCategoryFilms(short categoryId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        Category category = categoryDAO.get(categoryId);
        List<FilmCategory> filmCategoryList = category.getFilmCategoryList();
        List<FilmDto> filmDtoList = filmCategoryList.stream().map(FilmCategory::getFilm).map((film -> filmMapper.toDto(film))).toList();
        dbFactory.closeEntityManager(entityManager);
        return filmDtoList;
    }
    public int getCategoryFilmsCount(short categoryId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        Category category = categoryDAO.get(categoryId);
        int count = category.getFilmCategoryList().size();
        dbFactory.closeEntityManager(entityManager);
        return count;
    }

    public boolean addCategory(String categoryName) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);

        Category category = new Category();
        category.setName(categoryName);
        category.setLastUpdate(new Date());

        boolean result = categoryDAO.save(category);

        dbFactory.closeEntityManager(entityManager);
        return result;
    }

    public boolean editCategory(Short categoryId, String categoryName) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);

        Category category = categoryDAO.get(categoryId);
        category.setName(categoryName);
        category.setLastUpdate(new Date());

        boolean result = categoryDAO.update(category);

        dbFactory.closeEntityManager(entityManager);
        return result;
    }


//    public List<FilmDto> getCategoryFilmsForActor(short categoryId, short actorId) {
//        ActorDAO actorDAO = new ActorDAO();
//        Actor actor = actorDAO.get(actorId);
//        List<FilmActor> filmActorList = actor.getFilmActorList();
//        List<Film> filmList = filmActorList.stream().map(FilmActor::getFilm).filter(Film::)
//                .toList();
//        List<FilmDto> filmDtoList = filmMapper.toDTOs(filmList);
//        return filmDtoList;
//    }

    // note connection closed list lazy
    private Category getCategory(Film film, short categoryId) {
        List<FilmCategory> filmCategoryList = film.getFilmCategoryList();
        Optional<Category> optionalCategory = filmCategoryList
                .stream()
                .map(FilmCategory::getCategory)
                .filter((category)->category.getCategoryId()==categoryId)
                .findFirst();
        if(optionalCategory.isPresent()) return optionalCategory.get();
        return null;
    }
}

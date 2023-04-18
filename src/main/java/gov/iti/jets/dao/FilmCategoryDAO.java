package gov.iti.jets.dao;

import gov.iti.jets.entity.FilmCategory;
import jakarta.persistence.EntityManager;

public class FilmCategoryDAO extends BaseDAO<FilmCategory> {

    public FilmCategoryDAO(EntityManager entityManager) {
        super(FilmCategory.class,entityManager);
    }
}

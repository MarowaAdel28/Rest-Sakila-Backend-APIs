package gov.iti.jets.dao;

import gov.iti.jets.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CategoryDAO extends BaseDAO<Category>{

    public CategoryDAO(EntityManager entityManager) {
        super(Category.class,entityManager);
    }

    public List<Category> getAllCategories() {
        String queryString = "from Category c ";
        Query q = entityManager.createQuery(queryString);
        return (List<Category>) q.getResultList();
    }

    public List<Category> searchByCategoryName(String name) {
        String queryString = "from Category c where c.name like :name";
        Query q = entityManager.createQuery(queryString)
                .setParameter("name", name.toUpperCase());
        return (List<Category>) q.getResultList();
    }
}

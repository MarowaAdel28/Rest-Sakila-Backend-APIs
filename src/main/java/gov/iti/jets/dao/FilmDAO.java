package gov.iti.jets.dao;

import gov.iti.jets.entity.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class FilmDAO extends BaseDAO<Film>{


    public FilmDAO(EntityManager entityManager) {
        super(Film.class, entityManager);
    }

    public List<Film> getAll() {
        String queryString = "from Film f ";
        Query q = entityManager.createQuery(queryString);
        return q.getResultList();
    }
}

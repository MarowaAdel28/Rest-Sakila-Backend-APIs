package gov.iti.jets.dao;

import gov.iti.jets.entity.FilmText;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class FilmTextDAO extends BaseDAO<FilmText> {

    public FilmTextDAO(EntityManager entityManager) {
        super(FilmText.class, entityManager);
    }

    public List<FilmText> getAll() {
        String queryString = "from FilmText f ";
        Query q = entityManager.createQuery(queryString);
        return q.getResultList();
    }
}

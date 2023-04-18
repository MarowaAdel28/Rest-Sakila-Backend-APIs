package gov.iti.jets.dao;

import gov.iti.jets.entity.Language;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class LanguageDAO extends BaseDAO<Language> {

    public LanguageDAO (EntityManager entityManager) {
        super(Language.class, entityManager);
    }

    public List<Language> getAll() {
        String queryString = "from Language l ";
        Query q = entityManager.createQuery(queryString);
        return q.getResultList();
    }
}

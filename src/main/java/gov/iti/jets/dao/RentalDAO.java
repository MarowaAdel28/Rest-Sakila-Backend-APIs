package gov.iti.jets.dao;

import gov.iti.jets.entity.Rental;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class RentalDAO extends BaseDAO<Rental> {
    public RentalDAO(EntityManager entityManager) {
        super(Rental.class, entityManager);
    }

    public List<Rental> getAll() {
        String queryString = "from Rental r ";
        Query q = entityManager.createQuery(queryString);
        return q.getResultList();
    }

    public Rental get(Integer id) {
        return entityManager.find(Rental.class,id);
    }
}

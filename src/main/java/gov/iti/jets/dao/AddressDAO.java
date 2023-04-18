package gov.iti.jets.dao;

import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class AddressDAO extends BaseDAO<Address> {

    public AddressDAO(EntityManager entityManager) {
        super(Address.class,entityManager);
    }

    public List<Address> getAll() {
        String queryString = "from Address a ";
        Query q = entityManager.createQuery(queryString);
        return q.getResultList();
    }
}

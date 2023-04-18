package gov.iti.jets.dao;

import gov.iti.jets.entity.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class StoreDAO extends BaseDAO<Store>{

    public StoreDAO(EntityManager entityManager) {
        super(Store.class,entityManager);
    }

    public List<Store> getAllStores() {
        String querySting = "From Store s";
        Query query = entityManager.createQuery(querySting);
        return query.getResultList();
    }
}

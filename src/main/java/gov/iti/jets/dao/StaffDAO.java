package gov.iti.jets.dao;

import gov.iti.jets.entity.Staff;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class StaffDAO extends BaseDAO<Staff> {
    public StaffDAO(EntityManager entityManager) {
        super(Staff.class, entityManager);
    }

    public List<Staff> getAll() {
        String queryString = "from Staff l ";
        Query q = entityManager.createQuery(queryString);
        return q.getResultList();
    }
}

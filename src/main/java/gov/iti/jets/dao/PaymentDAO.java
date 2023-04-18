package gov.iti.jets.dao;

import gov.iti.jets.entity.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class PaymentDAO extends BaseDAO<Payment>{
    public PaymentDAO(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }

    public List<Payment> getAll() {
        String queryString = "from Payment p ";
        Query q = entityManager.createQuery(queryString);
        return (List<Payment>) q.getResultList();
    }
}

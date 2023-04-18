package gov.iti.jets.dao;

import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CustomerDAO extends BaseDAO<Customer> {

    public CustomerDAO(EntityManager entityManager) {
        super(Customer.class,entityManager);
    }

    public List<Customer> getAllCustomers() {
        String querySting = "From Customer c";
        Query query = entityManager.createQuery(querySting);
        return query.getResultList();
    }

    public List<Customer> getAllActiveCustomers() {
        String querySting = "From Customer c where c.active = true";
        Query query = entityManager.createQuery(querySting);
        return query.getResultList();
    }

    public List<Customer> getAllInactiveCustomers() {
        String querySting = "From Customer c where c.active = false ";
        Query query = entityManager.createQuery(querySting);
        return query.getResultList();
    }

    public List<Customer> getAllCustomersInStore(Short storeId) {
        String querySting = "From Customer c where c.storeId =:store ";
        Query query = entityManager.createQuery(querySting).setParameter("store",storeId);
        return query.getResultList();
    }

    public List<Customer> searchCustomerByName(String name) {
        String queryString = "from Customer c where c.firstName like :fname or c.lastName like :lname";
        Query q = entityManager.createQuery(queryString)
                .setParameter("fname", name.toUpperCase())
                .setParameter("lname", name.toUpperCase());
        return q.getResultList();
    }
}

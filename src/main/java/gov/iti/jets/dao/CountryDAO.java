package gov.iti.jets.dao;

import gov.iti.jets.entity.City;
import gov.iti.jets.entity.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CountryDAO extends BaseDAO<Country> {

    public CountryDAO(EntityManager entityManager) {
        super(Country.class,entityManager);
    }

    public Country getCountryByName(String name) {
        String queryString = "from Country c where c.country = :name";
        Query q = entityManager.createQuery(queryString)
                .setParameter("name", name);
        return  (Country) q.getSingleResult();
    }

    public List<Country> getAllCountries() {
        String queryString = "from Country c";
        Query q = entityManager.createQuery(queryString);
        return  q.getResultList();
    }
}

package gov.iti.jets.dao;

import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CityDAO extends BaseDAO<City> {

    public CityDAO(EntityManager entityManager) {
        super(City.class,entityManager);
    }

    public City getCityByName(String name) {
        String queryString = "from City c where c.city = :name";
        Query q = entityManager.createQuery(queryString)
                .setParameter("name", name);
        return  (City) q.getSingleResult();
    }

    public List<City> getAllCities() {
        String queryString = "from City c";
        Query q = entityManager.createQuery(queryString);
        return  q.getResultList();
    }
}

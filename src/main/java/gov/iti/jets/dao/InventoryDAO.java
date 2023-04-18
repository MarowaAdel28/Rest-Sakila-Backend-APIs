package gov.iti.jets.dao;

import gov.iti.jets.dto.InventoryDto;
import gov.iti.jets.dto.RentalDto;
import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.Film;
import gov.iti.jets.entity.Inventory;
import gov.iti.jets.entity.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class InventoryDAO extends BaseDAO<Inventory> {

    public InventoryDAO(EntityManager entityManager) {
        super(Inventory.class,entityManager);
    }

    public List<Inventory> getAll() {
        String queryString = "from Inventory i ";
        Query q = entityManager.createQuery(queryString);
        return q.getResultList();
    }

//    public List<Film> getAllStoreFilms(Short storeId) {
//        String queryString = "from Inventory i where storeId =: store ";
//        Query q = entityManager.createQuery(queryString)
//                .setParameter("store",storeId);
//        return q.getResultList();
//    }

    public List<Inventory> getInventoryByFilm(Film film) {

        String queryString = "from Inventory i where i.filmId =: film ";
        Query q = entityManager.createQuery(queryString)
                .setParameter("film",film);
        return q.getResultList();
    }

    public List<Inventory> getInventoryByStore(Store store) {
        String queryString = "from Inventory i where i.storeId =: store ";
        Query q = entityManager.createQuery(queryString)
                .setParameter("store",store);
        return q.getResultList();
    }



}

package gov.iti.jets.dao;

import gov.iti.jets.entity.FilmActor;
import jakarta.persistence.EntityManager;

public class FilmActorDAO extends BaseDAO<FilmActor> {

    public FilmActorDAO(EntityManager entityManager) {
        super(FilmActor.class, entityManager);
    }
}

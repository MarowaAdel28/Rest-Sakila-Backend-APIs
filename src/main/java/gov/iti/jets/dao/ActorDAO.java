package gov.iti.jets.dao;

import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.FilmActor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ActorDAO extends BaseDAO<Actor>{

    public ActorDAO(EntityManager entityManager) {
        super(Actor.class,entityManager);
    }

    public List<Actor> getAllActors() {
        String queryString = "from Actor a ";
        Query q = entityManager.createQuery(queryString);
        return (List<Actor>) q.getResultList();
    }
    public List<Actor> searchActorByName(String name) {
        String queryString = "from Actor a where a.firstName like :fname or a.lastName like :lname";
        Query q = entityManager.createQuery(queryString)
                .setParameter("fname", name.toUpperCase())
                .setParameter("lname", name.toUpperCase());
        return (List<Actor>) q.getResultList();
    }

    public Actor getActorByName(String fname, String lname) {
        String queryString = "from Actor a where a.firstName = :fname and a.lastName = :lname";
        Query q = entityManager.createQuery(queryString)
                .setParameter("fname", fname)
                .setParameter("lname", lname);
        return (Actor) q.getSingleResult();
    }

    public List<FilmActor> getActorFilmList(Actor actor) {
        return actor.getFilmActorList();
    }

    public List<FilmActor> getActorFilmListByLanguage(Actor actor, String languageName) {
        List<FilmActor> filmActorList = actor.getFilmActorList().stream()
                .filter((film)->
                    film.getFilm().getLanguageId().getName().equals(languageName)
                ).toList();
        return filmActorList;
    }

    public List<FilmActor> getActorFilmListByRating(Actor actor, String rate) {
        List<FilmActor> filmActorList = actor.getFilmActorList().stream()
                .filter((film)->
                        film.getFilm().getRating().equals(rate)
                ).toList();
        return filmActorList;
    }

//    public List<FilmActor> getActorFilmList(Actor actor) {
//        return actor.getFilmActorList();
//    }
//
//    public List<FilmActor> getActorFilmListByLanguage(Actor actor, String languageName) {
//        List<FilmActor> filmActorList = actor.getFilmActorList().stream()
//                .filter((film)->
//                        film.getFilm().getLanguageId().getName().equals(languageName)
//                ).toList();
//        return filmActorList;
//    }
//
//    public List<FilmActor> getActorFilmListByRating(Actor actor, String rate) {
//
//        List<FilmActor> filmActorList = actor.getFilmActorList().stream()
//                .filter((film)->
//                        film.getFilm().getRating().equals(rate)
//                ).toList();
//        return filmActorList;
//    }

    public int getActorFilmCount(Actor actor) {

        return actor.getFilmActorList().size();
    }

    public int getActorFilmCountByLanguage(Actor actor, String languageName) {

        List<FilmActor> filmActorList = actor.getFilmActorList().stream()
                .filter((film)->
                        film.getFilm().getLanguageId().getName().equals(languageName)
                ).toList();
        return filmActorList.size();
    }

    public int getActorFilmCountByRating(Actor actor, String rate) {
        List<FilmActor> filmActorList = actor.getFilmActorList().stream()
                .filter((film)->
                        film.getFilm().getRating().equals(rate)
                ).toList();
        return filmActorList.size();
    }
}

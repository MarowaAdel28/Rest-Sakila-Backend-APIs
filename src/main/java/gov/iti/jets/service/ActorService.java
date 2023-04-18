package gov.iti.jets.service;

import gov.iti.jets.dao.ActorDAO;
import gov.iti.jets.dao.DBFactory;
import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.dto.ActorFormDto;
import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.FilmActor;
import gov.iti.jets.mapper.ActorFormMapper;
import gov.iti.jets.mapper.ActorMapper;
import gov.iti.jets.mapper.FilmMapper;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

public class ActorService {
    private volatile static ActorService actorService;

    private ActorMapper actorMapper;

    private FilmMapper filmMapper;

    private ActorFormMapper actorFormMapper;

    public static ActorService getInstance() {
        if (actorService == null) {
            synchronized (ActorService.class) {
                if (actorService == null) {
                    actorService = new ActorService();
                }
            }
        }
        return actorService;
    }

    private ActorService() {
        actorMapper = Mappers.getMapper(ActorMapper.class);
        filmMapper = Mappers.getMapper(FilmMapper.class);
        actorFormMapper = Mappers.getMapper(ActorFormMapper.class);
    }

    // get all actors
    public List<ActorDto> getActorList() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        List<Actor> actors = actorDAO.getAllActors();
        List<ActorDto> actorDtoList = actorMapper.toDTOs(actors);
        dbFactory.closeEntityManager(entityManager);
        return actorDtoList;
    }

    // get actor by id
    public ActorDto getActorById(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        Actor actor = actorDAO.get(id);
        ActorDto actorDto = actorMapper.toDto(actor);
        dbFactory.closeEntityManager(entityManager);
        return actorDto;
    }

    // search actor by full name
    public  List<ActorDto> searchActorByName(String name) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        List<Actor> actors = actorDAO.searchActorByName(name);
        List<ActorDto> actorDtoList = actorMapper.toDTOs(actors);
        dbFactory.closeEntityManager(entityManager);
        return actorDtoList;
    }

    // get list of actor films by actor id
    public List<FilmDto> getActorFilmList(short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        Actor actor = actorDAO.get(id);
        List<FilmActor> filmActors = actorDAO.getActorFilmList(actor);
        List<FilmDto> filmDtoList = filmActors.stream().map(FilmActor::getFilm).map(filmMapper::toDto).toList();
        dbFactory.closeEntityManager(entityManager);
        return filmDtoList;
    }

    // get list of actor films by actor id and specific language
    public List<FilmDto> getActorFilmListByLanguage(short id, String language) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        Actor actor = actorDAO.get(id);
        List<FilmActor> filmActors = actorDAO.getActorFilmListByLanguage(actor, language);
        List<FilmDto> filmDtoList = filmActors.stream().map(FilmActor::getFilm).map(filmMapper::toDto).toList();
        dbFactory.closeEntityManager(entityManager);
        return filmDtoList;
    }
    // get list of actor films by actor id and specific rating
    public List<FilmDto> getActorFilmListByRating(short id, String rating) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        Actor actor = actorDAO.get(id);
        List<FilmActor> filmActors = actorDAO.getActorFilmListByRating(actor, rating);
        List<FilmDto> filmDtoList = filmActors.stream().map(FilmActor::getFilm).map(filmMapper::toDto).toList();
        dbFactory.closeEntityManager(entityManager);
        return filmDtoList;
    }

    // get list of actor films by actor first and last name

//    public List<FilmDto> getActorFilmList(String fname,String lname) {
//        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
//        EntityManager entityManager = dbFactory.createEntityManager();
//        ActorDAO actorDAO = new ActorDAO(entityManager);
//        List<FilmActor> filmActors = actorDAO.getActorFilmList(fname,lname);
//        List<FilmDto> filmDtoList = filmActors.stream().map(FilmActor::getFilm).map(filmMapper::toDto).toList();
//        dbFactory.closeEntityManager(entityManager);
//        return filmDtoList;
//    }
//    // get list of actor films by actor first and last name and specific language
//    public List<FilmDto> getActorFilmListByLanguage(String fname, String lname, String language) {
//        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
//        EntityManager entityManager = dbFactory.createEntityManager();
//        ActorDAO actorDAO = new ActorDAO(entityManager);
//        List<FilmActor> filmActors = actorDAO.getActorFilmListByLanguage(fname,lname, language);
//        List<FilmDto> filmDtoList = filmActors.stream().map(FilmActor::getFilm).map(filmMapper::toDto).toList();
//        dbFactory.closeEntityManager(entityManager);
//        return filmDtoList;
//    }
//    // get list of actor films by actor first and last name and specific rating
//    public List<FilmDto> getActorFilmListByRating(String fname, String lname, String rating) {
//        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
//        EntityManager entityManager = dbFactory.createEntityManager();
//        ActorDAO actorDAO = new ActorDAO(entityManager);
//        List<FilmActor> filmActors = actorDAO.getActorFilmListByRating(fname,lname, rating);
//        List<FilmDto> filmDtoList = filmActors.stream().map(FilmActor::getFilm).map(filmMapper::toDto).toList();
//        dbFactory.closeEntityManager(entityManager);
//        return filmDtoList;
//    }

    // get count of actor film list
    public int getActorFilmCount(short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        Actor actor = actorDAO.get(id);
        int filmCount = actorDAO.getActorFilmCount(actor);
        dbFactory.closeEntityManager(entityManager);
        return filmCount;
    }
    // get count of actor films by actor id and specific language
    public int getActorFilmCountByLanguage(short id, String language) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        Actor actor = actorDAO.get(id);
        int filmCount = actorDAO.getActorFilmCountByLanguage(actor, language);
        dbFactory.closeEntityManager(entityManager);
        return filmCount;
    }
    // get count of actor films by actor id and specific rating
    public int getActorFilmCountByRating(short id, String rating) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);
        Actor actor = actorDAO.get(id);
        int filmCount = actorDAO.getActorFilmCountByRating(actor, rating);
        dbFactory.closeEntityManager(entityManager);
        return filmCount;
    }

    public boolean addActor(ActorFormDto actorDto) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        Actor actor = actorFormMapper.toEntity(actorDto);
        ActorDAO actorDAO = new ActorDAO(entityManager);
        actor.setLastUpdate(new Date());
        boolean isSaved = actorDAO.save(actor);
        dbFactory.closeEntityManager(entityManager);
        return isSaved;
    }

    public boolean editActor(short actorId, ActorFormDto actorDto) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        ActorDAO actorDAO = new ActorDAO(entityManager);

        Actor actor = actorFormMapper.toEntity(actorDto);
        actor.setActorId(actorId);
        actor.setLastUpdate(new Date());

        boolean isUpdated = actorDAO.update(actor);

        dbFactory.closeEntityManager(entityManager);

        return isUpdated;
    }

}

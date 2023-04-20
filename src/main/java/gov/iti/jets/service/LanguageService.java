package gov.iti.jets.service;

import gov.iti.jets.dao.DBFactory;
import gov.iti.jets.dao.LanguageDAO;
import gov.iti.jets.dto.LanguageDto;
import gov.iti.jets.entity.Language;
import gov.iti.jets.mapper.LanguageMapper;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

public class LanguageService {

    private volatile static LanguageService languageService;

    private LanguageMapper languageMapper;

    private LanguageService() {

        languageMapper = Mappers.getMapper(LanguageMapper.class);

    }

    public static LanguageService getInstance() {
        if(languageService == null) {
            synchronized (LanguageService.class) {
                if (languageService == null) {
                    languageService = new LanguageService();
                }
            }
        }
        return languageService;
    }

    public LanguageDto getById(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        LanguageDAO languageDAO = new LanguageDAO(entityManager);

        Language language = languageDAO.get(id);

        LanguageDto languageDto = languageMapper.toDto(language);

        dbFactory.closeEntityManager(entityManager);
        return languageDto;
    }

    public List<LanguageDto> getAll() {

        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        LanguageDAO languageDAO = new LanguageDAO(entityManager);

        List<Language> languageList = languageDAO.getAll();

        List<LanguageDto> languageDtoList = languageMapper.toDTOs(languageList);

        dbFactory.closeEntityManager(entityManager);

        return languageDtoList;
    }

    public boolean addLanguage(String languageName) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        LanguageDAO languageDAO = new LanguageDAO(entityManager);

        Language language = new Language();
        language.setName(languageName);
        language.setLastUpdate(new Date());

        boolean result = languageDAO.save(language);

        dbFactory.closeEntityManager(entityManager);
        return result;
    }

    public boolean editLanguage(Short languageId, String languageName) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        LanguageDAO languageDAO = new LanguageDAO(entityManager);

        Language language = languageDAO.get(languageId);
        language.setName(languageName);
        language.setLastUpdate(new Date());

        boolean result = languageDAO.update(language);

        dbFactory.closeEntityManager(entityManager);
        return result;
    }

    public boolean deleteLanguage(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        LanguageDAO languageDAO = new LanguageDAO(entityManager);

        entityManager.getTransaction().begin();

        Language language = languageDAO.get(id);

        boolean result = true;

        if(language.getFilmList().size() !=0) {
            result = false;
        }

        if (result) {
            result = languageDAO.delete(language);
        }
        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);
        return result;
    }
}

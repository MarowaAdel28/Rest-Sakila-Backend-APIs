package gov.iti.jets.service;

import gov.iti.jets.dao.DBFactory;
import gov.iti.jets.dao.FilmDAO;
import gov.iti.jets.dao.InventoryDAO;
import gov.iti.jets.dao.StoreDAO;
import gov.iti.jets.dto.FilmDto;
import gov.iti.jets.dto.InventoryDto;
import gov.iti.jets.entity.Film;
import gov.iti.jets.entity.Inventory;
import gov.iti.jets.custommapper.CustomInventoryMapper;
import gov.iti.jets.entity.Store;
import gov.iti.jets.mapper.FilmMapper;
import gov.iti.jets.mapper.InventoryMapper;
import gov.iti.jets.mapper.RentalMapper;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private InventoryMapper inventoryMapper;
    private volatile static InventoryService inventoryService;

    private CustomInventoryMapper customInventoryMapper;

    private FilmMapper filmMapper;

    private RentalMapper rentalMapper;

    private InventoryService() {
        inventoryMapper = Mappers.getMapper(InventoryMapper.class);
        customInventoryMapper = CustomInventoryMapper.getInstance();
        filmMapper = Mappers.getMapper(FilmMapper.class);
        rentalMapper = Mappers.getMapper(RentalMapper.class);
    }
    public static InventoryService getInstance() {
        if(inventoryService==null) {
            synchronized (InventoryService.class) {
                if(inventoryService==null) {
                    inventoryService = new InventoryService();
                }
            }
        }
        return inventoryService;
    }

    public InventoryDto getById(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        InventoryDAO inventoryDAO = new InventoryDAO(entityManager);

        Inventory inventory = inventoryDAO.get(id);
        InventoryDto inventoryDto = customInventoryMapper.toInventoryDto(inventory);

        dbFactory.closeEntityManager(entityManager);
        return inventoryDto;
    }

    public List<InventoryDto> getAll() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        InventoryDAO inventoryDAO = new InventoryDAO(entityManager);

        List<Inventory> inventoryList = inventoryDAO.getAll();

        List<InventoryDto> inventoryDtoList = new ArrayList<>();

        inventoryList.forEach((inventory -> {
            InventoryDto inventoryDto = customInventoryMapper.toInventoryDto(inventory);
            inventoryDtoList.add(inventoryDto);
        }));

        dbFactory.closeEntityManager(entityManager);

        return inventoryDtoList;
    }

    public List<InventoryDto> getByFilm(Integer filmId) {

        Short shortId = (Short) filmId.shortValue();
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        FilmDAO filmDAO = new FilmDAO(entityManager);
        Film film = filmDAO.get(shortId);

        InventoryDAO inventoryDAO = new InventoryDAO(entityManager);

        List<Inventory> inventoryList = inventoryDAO.getInventoryByFilm(film);

        List<InventoryDto> inventoryDtoList = new ArrayList<>();

        inventoryList.forEach((inventory -> {
            InventoryDto inventoryDto = customInventoryMapper.toInventoryDto(inventory);
            inventoryDtoList.add(inventoryDto);
        }));

        dbFactory.closeEntityManager(entityManager);
        return inventoryDtoList;
    }

    public List<InventoryDto> getByStore(Short storeId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        InventoryDAO inventoryDAO = new InventoryDAO(entityManager);

        StoreDAO storeDAO = new StoreDAO(entityManager);

        Store store = storeDAO.get(storeId);

        List<Inventory> inventoryList = inventoryDAO.getInventoryByStore(store);

        List<InventoryDto> inventoryDtoList = new ArrayList<>();

        inventoryList.forEach((inventory -> {
            InventoryDto inventoryDto = customInventoryMapper.toInventoryDto(inventory);
            inventoryDtoList.add(inventoryDto);
        }));

        dbFactory.closeEntityManager(entityManager);
        return inventoryDtoList;
    }

    public List<FilmDto> getStoreFilms(Short storeId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        InventoryDAO inventoryDAO = new InventoryDAO(entityManager);

        StoreDAO storeDAO = new StoreDAO(entityManager);

        Store store = storeDAO.get(storeId);

        List<Inventory> inventoryList = inventoryDAO.getInventoryByStore(store);

        List<FilmDto> filmList = new ArrayList<>();

        inventoryList.forEach((inventory -> {

            filmList.add(filmMapper.toDto(inventory.getFilmId()));
        }));

        dbFactory.closeEntityManager(entityManager);
        return filmList;
    }


}

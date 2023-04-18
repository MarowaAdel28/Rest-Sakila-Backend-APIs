package gov.iti.jets.service;

import gov.iti.jets.dao.CityDAO;
import gov.iti.jets.dao.DBFactory;
import gov.iti.jets.dto.CityDto;
import gov.iti.jets.entity.City;
import gov.iti.jets.mapper.CityMapper;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class CityService {

    private volatile static CityService cityService;

    private CityMapper cityMapper;

    private CityService() {
        cityMapper = Mappers.getMapper(CityMapper.class);
    }

    public static CityService getInstance() {
        if(cityService==null) {
            synchronized (CityService.class) {
                if (cityService==null) {
                    cityService = new CityService();
                }
            }
        }
        return cityService;
    }

    public CityDto getCityById(short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CityDAO cityDAO = new CityDAO(entityManager);

        City city = cityDAO.get(id);

        CityDto cityDto = cityMapper.toDto(city);
        cityDto.setCountryName(city.getCountryId().getCountry());
        cityDto.setCountryID(city.getCountryId().getCountryId());

        dbFactory.closeEntityManager(entityManager);

        return cityDto;
    }

    public List<CityDto> getAllCities() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CityDAO cityDAO = new CityDAO(entityManager);

        List<City> cityList = cityDAO.getAllCities();

        List<CityDto> cityDtoList = cityMapper.toDTOs(cityList);

        for(int i=0;i<cityList.size();i++) {
            cityDtoList.get(i).setCountryID(cityList.get(i).getCountryId().getCountryId());
            cityDtoList.get(i).setCountryName(cityList.get(i).getCountryId().getCountry());
        }

        dbFactory.closeEntityManager(entityManager);

        return cityDtoList;
    }
}

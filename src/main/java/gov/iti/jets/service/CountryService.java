package gov.iti.jets.service;

import gov.iti.jets.dao.CountryDAO;
import gov.iti.jets.dao.DBFactory;
import gov.iti.jets.dto.CityDto;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.entity.City;
import gov.iti.jets.entity.Country;
import gov.iti.jets.mapper.CityMapper;
import gov.iti.jets.mapper.CountryMapper;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class CountryService {
    private volatile static CountryService countryService;

    private CountryMapper countryMapper;
    private CityMapper cityMapper;

    private CountryService() {
        countryMapper = Mappers.getMapper(CountryMapper.class);
        cityMapper = Mappers.getMapper(CityMapper.class);
    }

    public static CountryService getInstance() {
        if (countryService == null) {
            synchronized (CountryService.class) {
                if (countryService == null) {
                    countryService = new CountryService();
                }
            }
        }
        return countryService;
    }

    public CountryDto getCountryById(short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CountryDAO countryDAO = new CountryDAO(entityManager);

        Country country = countryDAO.get(id);

        dbFactory.closeEntityManager(entityManager);

        return countryMapper.toDto(country);
    }

    public List<CountryDto> getAllCountries() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CountryDAO countryDAO = new CountryDAO(entityManager);

        List<Country> countryList = countryDAO.getAllCountries();

        dbFactory.closeEntityManager(entityManager);

        return countryMapper.toDTOs(countryList);
    }

    public List<CityDto> getCountryCityList(short countryId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CountryDAO countryDAO = new CountryDAO(entityManager);

        Country country = countryDAO.get(countryId);

        List<City> cityList = country.getCityList();

        List<CityDto> cityDtoList = cityMapper.toDTOs(cityList);

        for(int i=0;i<cityList.size();i++) {
            cityDtoList.get(i).setCountryID(cityList.get(i).getCountryId().getCountryId());
            cityDtoList.get(i).setCountryName(cityList.get(i).getCountryId().getCountry());
        }

        dbFactory.closeEntityManager(entityManager);

        return cityDtoList;
    }
}

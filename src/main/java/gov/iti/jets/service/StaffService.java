package gov.iti.jets.service;

import gov.iti.jets.custommapper.CustomStaffMapper;
import gov.iti.jets.dao.*;
import gov.iti.jets.dto.StaffDto;
import gov.iti.jets.dto.StaffFormDto;
import gov.iti.jets.dto.StoreFormDto;
import gov.iti.jets.entity.Address;
import gov.iti.jets.entity.City;
import gov.iti.jets.entity.Staff;
import gov.iti.jets.entity.Store;
import gov.iti.jets.mapper.AddressMapper;
import gov.iti.jets.mapper.StaffMapper;
import gov.iti.jets.util.Utility;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffService {

    private volatile static StaffService service;

    private CustomStaffMapper customStaffMapper;

    private StaffMapper staffMapper;

    private AddressMapper addressMapper;

    private StaffService() {
        customStaffMapper = CustomStaffMapper.getInstance();
        staffMapper = Mappers.getMapper(StaffMapper.class);
        addressMapper = Mappers.getMapper(AddressMapper.class);
    }

    public static StaffService getInstance() {
        if (service == null) {
            synchronized (StaffService.class) {
                if (service == null) {
                    service = new StaffService();
                }
            }
        }
        return service;
    }

    public StaffDto getById(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StaffDAO staffDAO = new StaffDAO(entityManager);
        Staff staff = staffDAO.get(id);
        StaffDto staffDto = customStaffMapper.toStaffDto(staff);
        dbFactory.closeEntityManager(entityManager);

        return staffDto;
    }

    public List<StaffDto> getAll() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StaffDAO staffDAO = new StaffDAO(entityManager);
        List<Staff> staffList = staffDAO.getAll();
        List<StaffDto> staffDtoList = new ArrayList<>() ;

        for (Staff staff : staffList) {
            staffDtoList.add(customStaffMapper.toStaffDto(staff));
        }
        dbFactory.closeEntityManager(entityManager);

        return staffDtoList;
    }

    public boolean addStaff(StaffFormDto staffFormDto) {
        boolean result = false;

        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StaffDAO staffDAO = new StaffDAO(entityManager);
        StoreDAO storeDAO = new StoreDAO(entityManager);

        Staff staff = staffMapper.toEntity(staffFormDto);
        Store store = storeDAO.get(staffFormDto.getStoreID());

        staff.setLastUpdate(new Date());

        entityManager.getTransaction().begin();

        Address address = saveAddress(entityManager,staffFormDto);

        if(address!=null && store!=null) {
            staff.setAddressId(address);
            staff.setStoreId(store);
            staff.setPassword(Utility.hashPassword(staffFormDto.getPassword()));
            result = staffDAO.saveRow(staff);
        }

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);
        return result;
    }

    public boolean editStaff(Short staffId, StaffFormDto staffFormDto) {
        boolean result = false;

        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StaffDAO staffDAO = new StaffDAO(entityManager);
        StoreDAO storeDAO = new StoreDAO(entityManager);

        entityManager.getTransaction().begin();

        Store store = storeDAO.get(staffFormDto.getStoreID());

        Staff staff = staffDAO.get(staffId);

        Address address = saveAddress(entityManager,staffFormDto);

        if(address!=null && store!=null) {
            staff.setAddressId(address);
            staff.setStoreId(store);
            staff.setLastUpdate(new Date());
            staff.setActive(staffFormDto.isActive());
            staff.setEmail(staffFormDto.getEmail());
            staff.setFirstName(staffFormDto.getFirstName());
            staff.setLastName(staffFormDto.getLastName());
            staff.setUsername(staffFormDto.getUsername());
            staff.setPassword(Utility.hashPassword(staffFormDto.getPassword()));

            result = staffDAO.saveRow(staff);
        }

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);

        return result;
    }

    private Address saveAddress(EntityManager entityManager, StaffFormDto staffFormDto) {
        AddressDAO addressDAO = new AddressDAO(entityManager);
        CityDAO cityDAO = new CityDAO(entityManager);

        Address address = addressMapper.toEntity(staffFormDto);

        City city = cityDAO.get(staffFormDto.getCity());

        if(address!=null && city!=null) {
            address.setCityId(city);
            addressDAO.saveRow(address);
            return address;
        }
        return null;
    }
}

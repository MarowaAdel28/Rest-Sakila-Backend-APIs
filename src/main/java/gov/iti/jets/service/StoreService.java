package gov.iti.jets.service;

import gov.iti.jets.custommapper.CustomInventoryMapper;
import gov.iti.jets.custommapper.CustomStaffMapper;
import gov.iti.jets.dao.*;
import gov.iti.jets.dto.*;
import gov.iti.jets.entity.*;
import gov.iti.jets.mapper.*;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreService {

    private volatile static StoreService storeService;

    private StoreMapper storeMapper;
    private CustomerInfoMapper customerInfoMapper;

    private CustomStaffMapper customStaffMapper;

    private StoreFormMapper storeFormMapper;
//    private StaffMapper staffMapper;

//    private InventoryMapper inventoryMapper;

    private RentalMapper rentalMapper;

    private CustomInventoryMapper customInventoryMapper;

    private StoreService() {

        storeMapper = Mappers.getMapper(StoreMapper.class);
        customerInfoMapper = Mappers.getMapper(CustomerInfoMapper.class);
        storeMapper = Mappers.getMapper(StoreMapper.class);
//        inventoryMapper = Mappers.getMapper(InventoryMapper.class);
//        staffMapper = Mappers.getMapper(StaffMapper.class);
        rentalMapper = Mappers.getMapper(RentalMapper.class);
        customInventoryMapper = CustomInventoryMapper.getInstance();
        customStaffMapper = CustomStaffMapper.getInstance();
        storeFormMapper = Mappers.getMapper(StoreFormMapper.class);
    }

    public static StoreService getInstance() {
        if(storeService==null) {
            synchronized (StoreService.class) {
                if(storeService==null) {
                    storeService = new StoreService();
                }
            }
        }
        return storeService;
    }

    public StoreDto getStoreById(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);
        Store store =storeDAO.get(id);

        StoreDto storeDto = storeMapper.toDto(store);
        storeDto.setStoreAddress(store.getAddressId().getAddress());
        storeDto.setManagerName(store.getManagerStaffId().getFirstName()+" "+store.getManagerStaffId().getLastName());

        dbFactory.closeEntityManager(entityManager);

        return storeDto;
    }

    public List<StoreDto> getAllStores() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);
        List<Store> storeList = storeDAO.getAllStores();

        List<StoreDto> storeDtoList = storeMapper.toDTOs(storeList);

        for(int i=0;i<storeList.size();i++) {
            Store store = storeList.get(i);
            storeDtoList.get(i).setStoreAddress(store.getAddressId().getAddress());
            storeDtoList.get(i).setManagerName(store.getManagerStaffId().getFirstName() + " " +
                    store.getManagerStaffId().getLastName());
        }
        dbFactory.closeEntityManager(entityManager);
        return storeDtoList;
    }

    public List<CustomerInfoDto> getStoreCustomerList(Short StoreId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);
        Store store =storeDAO.get(StoreId);

        List<Customer> customerList = store.getCustomerList();

        List<CustomerInfoDto> customerInfoDtoList = new ArrayList<>();
        customerList.forEach(customer -> {
            CustomerInfoDto customerInfoDto = customerInfoMapper.toDto(customer);
            customerInfoDto.setAddress(customer.getAddressId().getAddress());
            customerInfoDtoList.add(customerInfoDto);
        });

        dbFactory.closeEntityManager(entityManager);

        return customerInfoDtoList;
    }

    public List<StaffDto> getStoreStaffList(Short storeId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);
        Store store =storeDAO.get(storeId);

        List<Staff> staffList = store.getStaffList();

        List<StaffDto> staffDtoList = new ArrayList<>();

        for (Staff staff : staffList) {
            staffDtoList.add(customStaffMapper.toStaffDto(staff));
        }

        dbFactory.closeEntityManager(entityManager);

        return staffDtoList;
    }

    // issue
    public List<InventoryDto> getStoreInventoryList(Short storeId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);
        Store store =storeDAO.get(storeId);

        List<Inventory> inventoryList = store.getInventoryList();

        List<InventoryDto> inventoryDtos = new ArrayList<>();

        inventoryList.forEach((inventory -> {

            InventoryDto inventoryDto = customInventoryMapper.toInventoryDto(inventory);

            inventoryDtos.add(inventoryDto);
        }));

        dbFactory.closeEntityManager(entityManager);

        return inventoryDtos;
    }


    public boolean addStore(StoreFormDto storeFormDto) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);

        entityManager.getTransaction().begin();
        Address address = saveAddress(entityManager,storeFormDto);

        boolean result = false;

        Store store = new Store();
        store.setLastUpdate(new Date());

        if(storeFormDto.getStoreManager()!=null && address!=null) {
            StaffDAO staffDAO = new StaffDAO(entityManager);
            Staff staff = staffDAO.get(storeFormDto.getStoreManager());
            store.setManagerStaffId(staff);
            store.setAddressId(address);
            result = storeDAO.saveRow(store);
        }

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);

        return result;
    }

    public boolean editStore(Short storeId, StoreFormDto storeFormDto) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);

        entityManager.getTransaction().begin();

        Address address = saveAddress(entityManager,storeFormDto);

        boolean result = false;

        Store store = storeDAO.get(storeId);
        store.setLastUpdate(new Date());

        if(storeFormDto.getStoreManager()!=null && address != null) {
            StaffDAO staffDAO = new StaffDAO(entityManager);
            Staff staff = staffDAO.get(storeFormDto.getStoreManager());
            store.setManagerStaffId(staff);
            store.setAddressId(address);
            result = storeDAO.saveRow(store);
        }

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);

        return result;
    }

    private Address saveAddress(EntityManager entityManager,StoreFormDto storeFormDto) {
        AddressDAO addressDAO = new AddressDAO(entityManager);
        CityDAO cityDAO = new CityDAO(entityManager);

        City city = cityDAO.get(storeFormDto.getCity());
        Address address = storeFormMapper.toAddressEntity(storeFormDto);
        if(address!=null && city!=null) {
            address.setCityId(city);
            addressDAO.saveRow(address);
            return address;
        }
        return null;
    }

    public boolean deleteStore(Short id) {

        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        StoreDAO storeDAO = new StoreDAO(entityManager);

        entityManager.getTransaction().begin();

        Store store = storeDAO.get(id);

        boolean result = storeDAO.delete(store);

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);
        return result;
    }
}

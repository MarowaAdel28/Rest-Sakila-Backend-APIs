package gov.iti.jets.service;

import gov.iti.jets.custommapper.CustomPaymentMapper;
import gov.iti.jets.custommapper.CustomRentalMapper;
import gov.iti.jets.dao.*;
import gov.iti.jets.dto.*;
import gov.iti.jets.entity.*;
import gov.iti.jets.mapper.*;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService {

    private volatile static CustomerService customerService;

    private CustomPaymentMapper customPaymentMapper;

    private CustomRentalMapper customRentalMapper;

    private CustomerInfoMapper customerInfoMapper;

    private CustomerFormMapper customerFormMapper;

    private CustomerService() {

        customPaymentMapper = CustomPaymentMapper.getInstance();
        customRentalMapper = CustomRentalMapper.getInstance();
        customerInfoMapper = Mappers.getMapper(CustomerInfoMapper.class);
        customerFormMapper = Mappers.getMapper(CustomerFormMapper.class);
    }

    public static CustomerService getCustomerService() {
        if(customerService==null) {
            synchronized (CustomerService.class) {
                if(customerService==null) {
                    customerService = new CustomerService();
                }
            }
        }
        return customerService;
    }

    public CustomerInfoDto getCustomerById(short customerId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CustomerDAO customerDAO = new CustomerDAO(entityManager);

        Customer customer = customerDAO.get(customerId);

        CustomerInfoDto customerInfoDto = customerInfoMapper.toDto(customer);

        customerInfoDto.setAddress(customer.getAddressId().getAddress());

        dbFactory.closeEntityManager(entityManager);

        return customerInfoDto;
    }

    public List<CustomerInfoDto> getAllCustomers() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        List<Customer> customerList = customerDAO.getAllCustomers();

        List<CustomerInfoDto> customerInfoDtoList = toDtos(customerList);

        dbFactory.closeEntityManager(entityManager);
        return customerInfoDtoList;
    }

    public List<CustomerInfoDto> getAllActiveCustomers() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        List<Customer> customerList = customerDAO.getAllActiveCustomers();
        List<CustomerInfoDto> customerInfoDtoList = toDtos(customerList);

        dbFactory.closeEntityManager(entityManager);
        return customerInfoDtoList;
    }

    public List<CustomerInfoDto> getAllInactiveCustomers() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        List<Customer> customerList = customerDAO.getAllInactiveCustomers();
        List<CustomerInfoDto> customerInfoDtoList = toDtos(customerList);
        dbFactory.closeEntityManager(entityManager);
        return customerInfoDtoList;
    }

    public int getAllCustomersCount() {
        return getAllCustomers().size();
    }

    public int getAllActiveCustomersCount() {
        return getAllActiveCustomers().size();
    }

    public int getAllInactiveCustomersCount() {
        return getAllInactiveCustomers().size();
    }

    public List<PaymentDto> getCustomerPayment(short customerId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        Customer customer = customerDAO.get(customerId);
        if(customer == null) {
            return null;
        }
        List<Payment> paymentList = customer.getPaymentList();
        List<PaymentDto> paymentDtoList = new ArrayList<>();
        paymentList.forEach(payment -> {
            paymentDtoList.add(customPaymentMapper.toPaymentDto(payment));
        });
        dbFactory.closeEntityManager(entityManager);
        return paymentDtoList;
    }

    public BigDecimal getTotalAmount(short customerId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);

        Customer customer = customerDAO.get(customerId);
        if(customer == null) {
            return new BigDecimal(0);
        }
        List<Payment> paymentList = customer.getPaymentList();

        BigDecimal totalAmount = paymentList.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        dbFactory.closeEntityManager(entityManager);

        return totalAmount;
    }

    public List<RentalDto> getCustomerRental(short customerId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);

        Customer customer = customerDAO.get(customerId);
        if(customer == null) {
            return null;
        }

        List<Rental> rentalList = customer.getRentalList();
        List<RentalDto> rentalDtoList = new ArrayList<>();

        for (Rental rental : rentalList) {
            rentalDtoList.add(customRentalMapper.toRentalDto(rental));
        }

        dbFactory.closeEntityManager(entityManager);

        return rentalDtoList;
    }
    public int getCustomerRentalCount(short customerId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);

        Customer customer = customerDAO.get(customerId);
        if(customer == null) {
            return 0;
        }
        List<Rental> rentalList = customer.getRentalList();

        int count = rentalList.size();

        dbFactory.closeEntityManager(entityManager);

        return count;
    }

    public List<CustomerInfoDto> searchByName(String name) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        List<Customer> customerList = customerDAO.searchCustomerByName(name);

        List<CustomerInfoDto> customerInfoDtoList = toDtos(customerList);

        dbFactory.closeEntityManager(entityManager);

        return customerInfoDtoList;
    }

    public boolean addCustomer(CustomerFormDto customerDto) {
        boolean isSaved = false;
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        StoreDAO storeDAO = new StoreDAO(entityManager);

        Store store = storeDAO.get(customerDto.getStore());
        Customer customer = customerFormMapper.toEntity(customerDto);

        entityManager.getTransaction().begin();

        Address address = addAddress(entityManager,customerDto);

        if(address != null) {

            customer.setAddressId(address);
            customer.setStoreId(store);
            isSaved = customerDAO.saveRow(customer);
        }
        dbFactory.commitTransaction(entityManager,isSaved);
        dbFactory.closeEntityManager(entityManager);

        return isSaved;

    }

    public boolean editCustomer(Short id, CustomerFormDto customerDto) {

        boolean isSaved = false;
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        StoreDAO storeDAO = new StoreDAO(entityManager);

        entityManager.getTransaction().begin();

        Store store = storeDAO.get(customerDto.getStore());

        Customer customer = customerDAO.get(id);

        Short originalAddressId = customer.getAddressId().getAddressId();

        Address address = editAddress(entityManager,customerDto,originalAddressId);

        if(address!=null) {
            customer.setLastUpdate(new Date());
            customer.setAddressId(address);
            customer.setStoreId(store);
            customer.setActive(customerDto.isActive());
            customer.setEmail(customerDto.getEmail());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());

            isSaved = customerDAO.saveRow(customer);
        }
        dbFactory.commitTransaction(entityManager,isSaved);
        dbFactory.closeEntityManager(entityManager);

        return isSaved;
    }

    private List<CustomerInfoDto> toDtos(List<Customer> customerList) {
        List<CustomerInfoDto> customerInfoDtoList = new ArrayList<>();

        for(int i=0; i<customerList.size();i++) {
            Customer customer = customerList.get(i);
            CustomerInfoDto customerInfoDto = customerInfoMapper.toDto(customer);

            customerInfoDto.setAddress(customer.getAddressId().getAddress());

            customerInfoDtoList.add(customerInfoDto);
        }
        return customerInfoDtoList;
    }

    private Address addAddress(EntityManager entityManager, CustomerFormDto customerDto) {
        CityDAO cityDAO = new CityDAO(entityManager);
        AddressDAO addressDAO = new AddressDAO(entityManager);

        City city = cityDAO.get(customerDto.getCity());

        Address address = customerFormMapper.toAddressEntity(customerDto);
        address.setCityId(city);
        address.setLastUpdate(new Date());

        if(addressDAO.saveRow(address)) {
            return address;
        }
        return null;
    }

    private Address editAddress(EntityManager entityManager, CustomerFormDto customerFormDto, Short addressId) {
        CityDAO cityDAO = new CityDAO(entityManager);
        AddressDAO addressDAO = new AddressDAO(entityManager);

        City city = cityDAO.get(customerFormDto.getCity());

        Address address = addressDAO.get(addressId);

        address.setDistrict(customerFormDto.getDistrict());
        address.setAddress(customerFormDto.getAddress());
        address.setPhone(customerFormDto.getPhone());
        address.setPostalCode(customerFormDto.getPostalCode());
        address.setCityId(city);
        address.setLastUpdate(new Date());

        if(addressDAO.saveRow(address)) {
            return address;
        }
        return null;
    }
}

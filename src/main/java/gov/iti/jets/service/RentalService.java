package gov.iti.jets.service;

import gov.iti.jets.custommapper.CustomPaymentMapper;
import gov.iti.jets.custommapper.CustomRentalMapper;
import gov.iti.jets.dao.*;
import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.dto.RentalDto;
import gov.iti.jets.dto.RentalFormDto;
import gov.iti.jets.entity.*;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentalService {

    private volatile static RentalService rentalService;
    private CustomRentalMapper customRentalMapper;

    private CustomPaymentMapper customPaymentMapper;
    private RentalService() {
        customRentalMapper = CustomRentalMapper.getInstance();
        customPaymentMapper = CustomPaymentMapper.getInstance();
    }

    public static RentalService getInstance() {
        if (rentalService == null) {
            synchronized (RentalService.class) {
                if (rentalService == null) {
                    rentalService = new RentalService();
                }
            }
        }
        return rentalService;
    }

    public RentalDto getById(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        RentalDAO rentalDAO = new RentalDAO(entityManager);

        Rental rental = rentalDAO.get(id);
        RentalDto rentalDto = customRentalMapper.toRentalDto(rental);

        dbFactory.closeEntityManager(entityManager);
        return rentalDto;
    }

    public List<RentalDto> getAll() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        RentalDAO rentalDAO = new RentalDAO(entityManager);

        List<Rental> rentalList = rentalDAO.getAll();
        List<RentalDto> rentalDtoList = new ArrayList<>();

        rentalList.forEach((rental -> rentalDtoList.add(customRentalMapper.toRentalDto(rental))));

        dbFactory.closeEntityManager(entityManager);
        return rentalDtoList;
    }

    public List<PaymentDto> getPaymentList(Short rentalId) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();
        RentalDAO rentalDAO = new RentalDAO(entityManager);

        Rental rental = rentalDAO.get(rentalId);
        List<Payment> paymentList = rental.getPaymentList();
        List<PaymentDto> paymentDtoList = new ArrayList<>();

        paymentList.forEach((payment -> paymentDtoList.add(customPaymentMapper.toPaymentDto(payment))));
        dbFactory.closeEntityManager(entityManager);

        return paymentDtoList;
    }

    public boolean addRental(RentalFormDto rentalFormDto) {
        boolean result = false;
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        RentalDAO rentalDAO = new RentalDAO(entityManager);
        PaymentDAO paymentDAO = new PaymentDAO(entityManager);
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        InventoryDAO inventoryDAO = new InventoryDAO(entityManager);
        StaffDAO staffDAO = new StaffDAO(entityManager);

        Customer customer = customerDAO.get(rentalFormDto.getCustomer());
        Inventory inventory = inventoryDAO.get(rentalFormDto.getInventory());
        Staff staff = staffDAO.get(rentalFormDto.getStaff());

        Rental rental = new Rental();
        rental.setRentalDate(new Date());
        rental = createRental(rental,rentalFormDto,customer,staff,inventory);

        entityManager.getTransaction().begin();

        result = rentalDAO.saveRow(rental);

        if(result) {
            // add payment
            result = paymentDAO.saveRow(createPayment(rental,customer,staff,rentalFormDto.getAmount()));
        }

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);
        return result;
    }

    public boolean editRental(Integer rentalId, RentalFormDto rentalFormDto) {
        boolean result = false;
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        RentalDAO rentalDAO = new RentalDAO(entityManager);
        CustomerDAO customerDAO = new CustomerDAO(entityManager);
        InventoryDAO inventoryDAO = new InventoryDAO(entityManager);
        StaffDAO staffDAO = new StaffDAO(entityManager);
        PaymentDAO paymentDAO = new PaymentDAO(entityManager);

        Customer customer = customerDAO.get(rentalFormDto.getCustomer());
        Inventory inventory = inventoryDAO.get(rentalFormDto.getInventory());
        Staff staff = staffDAO.get(rentalFormDto.getStaff());

        Rental rental = rentalDAO.get(rentalId);

        rental = createRental(rental,rentalFormDto,customer,staff,inventory);

        rental.setRentalId(rentalId);

        entityManager.getTransaction().begin();

        result = rentalDAO.saveRow(rental);

        if(result) {
            // add payment
            result = paymentDAO.saveRow(createPayment(rental,customer,staff,rentalFormDto.getAmount()));
        }

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);
        return result;
    }

    private Date craeteDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm dd yyyy HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    private Rental createRental(Rental rental, RentalFormDto rentalFormDto,Customer customer,Staff staff, Inventory inventory) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, rentalFormDto.getReturnDt());
        rental.setReturnDate(calendar.getTime());
        rental.setLastUpdate(new Date());
        rental.setStaffId(staff);
        rental.setCustomerId(customer);
        rental.setInventoryId(inventory);
        return rental;
    }

    private Payment createPayment(Rental rental, Customer customer, Staff staff, BigDecimal amount) {

        Payment payment = new Payment();

        payment.setRentalId(rental);
        payment.setPaymentDate(new Date());
        payment.setCustomerId(customer);
        payment.setAmount(amount);
        payment.setLastUpdate(new Date());
        payment.setStaffId(staff);

        return  payment;
    }

    public boolean deleteRental(Integer id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager =dbFactory.createEntityManager();

        RentalDAO rentalDAO = new RentalDAO(entityManager);

        boolean result = false;

        entityManager.getTransaction().begin();

        Rental rental = rentalDAO.get(id);

        result = rentalDAO.delete(rental);

        dbFactory.commitTransaction(entityManager,result);
        dbFactory.closeEntityManager(entityManager);
        return result;
    }
}

package gov.iti.jets.service;

import gov.iti.jets.custommapper.CustomPaymentMapper;
import gov.iti.jets.dao.DBFactory;
import gov.iti.jets.dao.PaymentDAO;
import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.entity.Payment;
import jakarta.persistence.EntityManager;


import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private volatile static PaymentService paymentService;
//    private PaymentMapper paymentMapper;
    private CustomPaymentMapper customPaymentMapper;
    private PaymentService() {
//        paymentMapper = Mappers.getMapper(PaymentMapper.class);
    customPaymentMapper = CustomPaymentMapper.getInstance();
    }

    public static PaymentService getInstance() {
        if (paymentService == null) {
            synchronized (PaymentService.class) {
                if (paymentService == null) {
                    paymentService = new PaymentService();
                }
            }
        }
        return paymentService;
    }

    public PaymentDto getById(Short id) {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        PaymentDAO paymentDAO = new PaymentDAO(entityManager);

        Payment payment = paymentDAO.get(id);
        PaymentDto paymentDto = customPaymentMapper.toPaymentDto(payment);

        dbFactory.closeEntityManager(entityManager);
        return paymentDto;
    }

    public List<PaymentDto> getAll() {
        DBFactory dbFactory = DBFactory.getDbFactoryInstance();
        EntityManager entityManager = dbFactory.createEntityManager();

        PaymentDAO paymentDAO = new PaymentDAO(entityManager);

        List<Payment> paymentList = paymentDAO.getAll();

        List<PaymentDto> paymentDtoList = new ArrayList<>();

        paymentList.forEach((payment -> paymentDtoList.add(customPaymentMapper.toPaymentDto(payment))));

        dbFactory.closeEntityManager(entityManager);
        return paymentDtoList;
    }
}

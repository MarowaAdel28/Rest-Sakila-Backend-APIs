package gov.iti.jets.custommapper;

import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.Payment;
import gov.iti.jets.mapper.PaymentMapper;
import org.mapstruct.factory.Mappers;

public class CustomPaymentMapper {

    private volatile static CustomPaymentMapper customPaymentMapper;
    private PaymentMapper paymentMapper;

    private CustomPaymentMapper() {
        paymentMapper = Mappers.getMapper(PaymentMapper.class);
    }

    public static CustomPaymentMapper getInstance() {
        if (customPaymentMapper == null) {
            synchronized (CustomPaymentMapper.class) {
                if (customPaymentMapper == null) {
                    customPaymentMapper = new CustomPaymentMapper();
                }
            }
        }
        return customPaymentMapper;
    }

    public PaymentDto toPaymentDto(Payment payment) {
        PaymentDto paymentDto = paymentMapper.toDto(payment);
        Customer customer = payment.getCustomerId();

        paymentDto.setCustomerID(customer.getCustomerId());
        paymentDto.setCustomerName(customer.getFirstName() +" " + customer.getLastName() );
        paymentDto.setRental(payment.getRentalId().getRentalId());
        paymentDto.setStaffName(payment.getStaffId().getFirstName() + " " +payment.getStaffId().getFirstName());
        paymentDto.setStaff(payment.getStaffId().getStaffId());

        return paymentDto;
    }
}

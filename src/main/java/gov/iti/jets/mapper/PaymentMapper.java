package gov.iti.jets.mapper;

import gov.iti.jets.dto.PaymentDto;
import gov.iti.jets.entity.Payment;
import jakarta.annotation.Generated;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")
public interface PaymentMapper {
    Payment toEntity(PaymentDto paymentDto);

    PaymentDto toDto(Payment payment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment partialUpdate(PaymentDto paymentDto, @MappingTarget Payment payment);

    default ArrayList<PaymentDto> toDTOs(Collection<Payment> payments) {
        return payments.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<PaymentDto>::new));
    }

    default ArrayList<Payment> toEntities(Collection<PaymentDto> paymentDtoS) {
        return paymentDtoS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Payment>::new));
    }
}
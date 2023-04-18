package gov.iti.jets.mapper;

import gov.iti.jets.dto.CustomerFormDto;
import gov.iti.jets.entity.Address;
import gov.iti.jets.entity.Customer;
import jakarta.annotation.Generated;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")

public interface CustomerFormMapper {
    Customer toEntity(CustomerFormDto customerFormDto);

    CustomerFormDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerFormDto customerFormDto, @MappingTarget Customer customer);

    Address toAddressEntity(CustomerFormDto customerFormDto);

//    AddingCustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(CustomerFormDto customerFormDto, @MappingTarget Address address);
}
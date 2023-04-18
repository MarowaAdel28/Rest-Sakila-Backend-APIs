package gov.iti.jets.mapper;

import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.Customer;
import jakarta.annotation.Generated;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")
public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerDto customerDto, @MappingTarget Customer customer);

    default ArrayList<CustomerDto> toDTOs(Collection<Customer> customers) {
        return customers.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<CustomerDto>::new));
    }

    default ArrayList<Customer> toEntities(Collection<CustomerDto> customerDtoS) {
        return customerDtoS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Customer>::new));
    }
}
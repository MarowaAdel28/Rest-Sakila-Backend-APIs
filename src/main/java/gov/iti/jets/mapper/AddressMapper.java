package gov.iti.jets.mapper;

import gov.iti.jets.dto.AddressDto;
import gov.iti.jets.dto.StaffFormDto;
import gov.iti.jets.entity.Address;
import jakarta.annotation.Generated;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
@Generated(value = "jakarta.annotation.Generated")
//@Generated("jakarta.annotation.Generated")
public interface AddressMapper {
    Address toEntity(AddressDto addressDto);
    Address toEntity(StaffFormDto staffDto);

    AddressDto toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressDto addressDto, @MappingTarget Address address);
}
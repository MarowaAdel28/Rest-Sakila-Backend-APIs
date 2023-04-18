package gov.iti.jets.mapper;

import gov.iti.jets.dto.StoreFormDto;
import gov.iti.jets.entity.Address;
import jakarta.annotation.Generated;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
@Generated(value = "jakarta.annotation.Generated")
//@Generated("jakarta.annotation.Generated")
public interface StoreFormMapper {
    Address toAddressEntity(StoreFormDto storeDto);

//    AddressDto toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(StoreFormDto storeDto, @MappingTarget Address address);
}
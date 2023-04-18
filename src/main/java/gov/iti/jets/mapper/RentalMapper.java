package gov.iti.jets.mapper;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RentalDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.Rental;
import jakarta.annotation.Generated;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")
public interface RentalMapper {
    Rental toEntity(RentalDto rentalDto);

    RentalDto toDto(Rental rental);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(RentalDto rentalDto, @MappingTarget Rental rental);

    default ArrayList<RentalDto> toDTOs(Collection<Rental> Rentals) {
        return Rentals.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<RentalDto>::new));
    }

    default ArrayList<Rental> toEntities(Collection<RentalDto> rentalDtoS) {
        return rentalDtoS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Rental>::new));
    }
}
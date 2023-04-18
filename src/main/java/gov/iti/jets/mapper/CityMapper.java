package gov.iti.jets.mapper;

import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.dto.CityDto;
import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.City;
import jakarta.annotation.Generated;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")
public interface CityMapper {
    City toEntity(CityDto cityDto);

    CityDto toDto(City city);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    City partialUpdate(CityDto cityDto, @MappingTarget City city);

    default ArrayList<CityDto> toDTOs(Collection<City> cities) {
        return cities.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<CityDto>::new));
    }

    default ArrayList<City> toEntities(Collection<CityDto> cityDtoS) {
        return cityDtoS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<City>::new));
    }
}
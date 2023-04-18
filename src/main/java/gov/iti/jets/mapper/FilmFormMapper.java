package gov.iti.jets.mapper;

import gov.iti.jets.dto.ActorFormDto;
import gov.iti.jets.dto.FilmFormDto;
import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.Film;
import jakarta.annotation.Generated;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")

public interface FilmFormMapper {
    Film toEntity(FilmFormDto filmFormDto);

    FilmFormDto toDto(Film film);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Film partialUpdate(FilmFormDto filmFormDto, @MappingTarget Film film);
}
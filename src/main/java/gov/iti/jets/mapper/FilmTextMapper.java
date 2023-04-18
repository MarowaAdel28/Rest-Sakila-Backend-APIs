package gov.iti.jets.mapper;

import gov.iti.jets.dto.FilmTextDto;
import gov.iti.jets.entity.FilmText;
import jakarta.annotation.Generated;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")

public interface FilmTextMapper {
    FilmText toEntity(FilmTextDto filmTextDto);

    FilmTextDto toDto(FilmText filmText);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilmText partialUpdate(FilmTextDto filmTextDto, @MappingTarget FilmText filmText);
}
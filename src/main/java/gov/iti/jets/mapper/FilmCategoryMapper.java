package gov.iti.jets.mapper;

import gov.iti.jets.dto.FilmCategoryDto;
import gov.iti.jets.entity.FilmCategory;
import jakarta.annotation.Generated;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {FilmCategoryPKMapper.class})
//@Generated("jakarta.annotation.Generated")

@Generated(value = "jakarta.annotation.Generated")
public interface FilmCategoryMapper {
    FilmCategory toEntity(FilmCategoryDto filmCategoryDto);

    FilmCategoryDto toDto(FilmCategory filmCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilmCategory partialUpdate(FilmCategoryDto filmCategoryDto, @MappingTarget FilmCategory filmCategory);
}
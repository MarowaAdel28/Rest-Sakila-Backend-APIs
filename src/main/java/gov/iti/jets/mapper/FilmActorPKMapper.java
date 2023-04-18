package gov.iti.jets.mapper;

import gov.iti.jets.entity.FilmActorPK;
import jakarta.annotation.Generated;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")
public interface FilmActorPKMapper {
    FilmActorPK toEntity(FilmActorPK filmActorPK);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilmActorPK partialUpdate(FilmActorPK filmActorPKDto, @MappingTarget FilmActorPK filmActorPK);
}
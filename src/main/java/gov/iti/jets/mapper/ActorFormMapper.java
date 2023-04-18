package gov.iti.jets.mapper;

import gov.iti.jets.dto.ActorFormDto;
import gov.iti.jets.entity.Actor;
import org.mapstruct.*;
import jakarta.annotation.Generated;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")

public interface ActorFormMapper {
    Actor toEntity(ActorFormDto actorFormDto);

    ActorFormDto toDto(Actor actor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Actor partialUpdate(ActorFormDto actorFormDto, @MappingTarget Actor actor);
}
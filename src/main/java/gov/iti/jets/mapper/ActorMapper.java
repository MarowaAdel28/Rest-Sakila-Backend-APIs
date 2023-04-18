package gov.iti.jets.mapper;

import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.entity.Actor;
import jakarta.annotation.Generated;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")

public interface ActorMapper {
    Actor toEntity(ActorDto actorDto);

    ActorDto toDto(Actor actor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Actor partialUpdate(ActorDto actorDto, @MappingTarget Actor actor);

    default ArrayList<ActorDto> toDTOs(Collection<Actor> actors) {
        return actors.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<ActorDto>::new));
    }

    default ArrayList<Actor> toEntities(Collection<ActorDto> actorDtoS) {
        return actorDtoS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Actor>::new));
    }
}
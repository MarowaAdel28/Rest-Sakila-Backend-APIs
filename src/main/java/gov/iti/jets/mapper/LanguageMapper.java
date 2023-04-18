package gov.iti.jets.mapper;

import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.dto.LanguageDto;
import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.Language;
import jakarta.annotation.Generated;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")
public interface LanguageMapper {
    Language toEntity(LanguageDto languageDto);

    LanguageDto toDto(Language language);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Language partialUpdate(LanguageDto languageDto, @MappingTarget Language language);

    default ArrayList<LanguageDto> toDTOs(Collection<Language> languages) {
        return languages.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<LanguageDto>::new));
    }

    default ArrayList<Language> toEntities(Collection<LanguageDto> languageDtoS) {
        return languageDtoS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Language>::new));
    }
}
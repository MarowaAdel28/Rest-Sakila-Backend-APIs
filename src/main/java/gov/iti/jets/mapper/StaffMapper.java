package gov.iti.jets.mapper;

import gov.iti.jets.dto.ActorDto;
import gov.iti.jets.dto.StaffDto;
import gov.iti.jets.dto.StaffFormDto;
import gov.iti.jets.entity.Actor;
import gov.iti.jets.entity.Staff;
import jakarta.annotation.Generated;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
//@Generated("jakarta.annotation.Generated")
@Generated(value = "jakarta.annotation.Generated")
public interface StaffMapper {
    Staff toEntity(StaffDto staffDto);

    StaffDto toDto(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Staff partialUpdate(StaffDto staffDto, @MappingTarget Staff staff);

    default ArrayList<StaffDto> toDTOs(Collection<Staff> staffs) {
        return staffs.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<StaffDto>::new));
    }

    default ArrayList<Staff> toEntities(Collection<StaffDto> staffDtoS) {
        return staffDtoS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Staff>::new));
    }
    Staff toEntity(StaffFormDto staffDto);
}
package gov.iti.jets.custommapper;

import gov.iti.jets.dto.StaffDto;
import gov.iti.jets.dto.StaffFormDto;
import gov.iti.jets.entity.Address;
import gov.iti.jets.entity.Staff;
import gov.iti.jets.mapper.StaffMapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;

public class CustomStaffMapper {

    private volatile static CustomStaffMapper customStaffMapper;

    private StaffMapper staffMapper;

    private CustomStaffMapper() {
        staffMapper = Mappers.getMapper(StaffMapper.class);
    }

    public static CustomStaffMapper getInstance(){
        if (customStaffMapper==null) {
            synchronized (CustomStaffMapper.class) {
                if (customStaffMapper==null) {
                    customStaffMapper = new CustomStaffMapper();
                }
            }
        }
        return customStaffMapper;
    }

    public StaffDto toStaffDto(Staff staff) {
        StaffDto staffDto = staffMapper.toDto(staff);
        Address address = staff.getAddressId();

        staffDto.setAddress(address.getAddress()+","+address.getDistrict()+","+address.getCityId().getCity()
                +","+address.getCityId().getCountryId().getCountry());
        staffDto.setStoreID(staff.getStoreId().getStoreId());

        return staffDto;
    }

    public Address toAddress(StaffFormDto staffFormDto) {
        Address address = new Address();

        address.setAddress(staffFormDto.getAddress());
        address.setPhone(staffFormDto.getPhone());
        address.setDistrict(staffFormDto.getDistrict());
        address.setPostalCode(staffFormDto.getPostalCode());
        address.setLastUpdate(new Date());

        return address;
    }
}

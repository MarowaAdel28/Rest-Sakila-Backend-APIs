package gov.iti.jets.custommapper;

import gov.iti.jets.dto.RentalDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.Rental;
import gov.iti.jets.mapper.RentalMapper;
import org.mapstruct.factory.Mappers;

public class CustomRentalMapper {

    private volatile static CustomRentalMapper customRentalMapper;

    private RentalMapper rentalMapper;

    private CustomRentalMapper() {
        rentalMapper = Mappers.getMapper(RentalMapper.class);
    }

    public static CustomRentalMapper getInstance(){
        if (customRentalMapper == null) {
            synchronized (CustomRentalMapper.class) {
                if (customRentalMapper == null) {
                    customRentalMapper = new CustomRentalMapper();
                }
            }
        }
        return customRentalMapper;
    }

    public RentalDto toRentalDto(Rental rental) {
        RentalDto rentalDto = rentalMapper.toDto(rental);

        Customer customer = rental.getCustomerId();

        rentalDto.setCustomerID(customer.getCustomerId());
        rentalDto.setCustomerName(customer.getFirstName() +" " + customer.getLastName() );
        rentalDto.setStaffName(rental.getStaffId().getFirstName() + " " +rental.getStaffId().getFirstName());
        rentalDto.setStaffID(rental.getStaffId().getStaffId());
        rentalDto.setInventoryID(rental.getInventoryId().getInventoryId());


        return rentalDto;
    }
}

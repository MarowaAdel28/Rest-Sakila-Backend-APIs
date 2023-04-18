package gov.iti.jets.custommapper;

import gov.iti.jets.dto.InventoryDto;
import gov.iti.jets.entity.Inventory;
import gov.iti.jets.entity.Rental;
import gov.iti.jets.mapper.InventoryMapper;
import gov.iti.jets.mapper.RentalMapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class CustomInventoryMapper {

    private volatile static CustomInventoryMapper customInventoryMapper;

    private RentalMapper rentalMapper;
    private InventoryMapper inventoryMapper;

    private CustomInventoryMapper() {
        inventoryMapper = Mappers.getMapper(InventoryMapper.class);
        rentalMapper = Mappers.getMapper(RentalMapper.class);
    }

    public static CustomInventoryMapper getInstance(){
        if (customInventoryMapper==null) {
            synchronized (CustomInventoryMapper.class) {
                if (customInventoryMapper==null) {
                    customInventoryMapper = new CustomInventoryMapper();
                }
            }
        }
        return customInventoryMapper;
    }

    public InventoryDto toInventoryDto(Inventory inventory) {
        InventoryDto inventoryDto = inventoryMapper.toDto(inventory);
        inventoryDto.setStoreID(inventory.getStoreId().getStoreId());
        inventoryDto.setFilmID(inventory.getFilmId().getFilmId());
        inventoryDto.setFilmName(inventory.getFilmId().getTitle());

        List<Rental> rentalList = inventory.getRentalList();
        inventoryDto.setRentalDtoList(rentalMapper.toDTOs(rentalList));

        return inventoryDto;
    }
}

package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link gov.iti.jets.entity.Inventory} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement
public class InventoryDto implements Serializable {
    private Integer inventoryId;
    private Date lastUpdate;
    private short storeID;
    private short filmID;
    private String filmName;
    private List<RentalDto> rentalDtoList;
}
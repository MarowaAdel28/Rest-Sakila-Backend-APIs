package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Rental} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class RentalDto implements Serializable {
    private Integer rentalId;
    private Date rentalDate;
    private Date returnDate;
    private Date lastUpdate;
    private Short customerID;
    private String customerName;
    private Short staffID;
    private String staffName;
    private Integer InventoryID;
}
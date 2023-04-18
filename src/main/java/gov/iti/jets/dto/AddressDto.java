package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Address} entity
 */
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto implements Serializable {
    private Short addressId;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private Date lastUpdate;
}
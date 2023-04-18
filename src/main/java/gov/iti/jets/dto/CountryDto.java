package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Country} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class CountryDto implements Serializable {
    private Short countryId;
    private String country;
    private Date lastUpdate;
}
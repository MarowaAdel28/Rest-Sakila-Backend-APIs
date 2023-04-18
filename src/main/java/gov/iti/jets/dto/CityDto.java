package gov.iti.jets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link gov.iti.jets.entity.City} entity
 */

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDto implements Serializable {
    private Short cityId;
    private String city;
    private Date lastUpdate;
    private short countryID;
    private String countryName;
}
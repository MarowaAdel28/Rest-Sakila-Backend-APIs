package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Film} entity
 */
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilmDto implements Serializable {
    private Short filmId;
    private String title;
    private String description;
    private int releaseYear;
    private short rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;
}
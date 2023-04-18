package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilmFormDto implements Serializable {

    private String title;
    private String description;
    private int releaseYear;
    private short rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private Short language;
    private Short category;
    private Short actor;
    private Short store;
}

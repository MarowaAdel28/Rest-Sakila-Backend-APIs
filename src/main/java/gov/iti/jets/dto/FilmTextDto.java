package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.jets.entity.FilmText} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement
public class FilmTextDto implements Serializable {
    private Short filmId;
    private String title;
    private String description;
}
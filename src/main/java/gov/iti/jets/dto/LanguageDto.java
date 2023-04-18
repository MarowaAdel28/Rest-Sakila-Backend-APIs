package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Language} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement
public class LanguageDto implements Serializable {
    private Short languageId;
    private String name;
    private Date lastUpdate;
}
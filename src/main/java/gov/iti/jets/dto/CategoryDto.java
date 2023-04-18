package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Category} entity
 */
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto implements Serializable {
    private Short categoryId;
    private String name;
    private Date lastUpdate;
}
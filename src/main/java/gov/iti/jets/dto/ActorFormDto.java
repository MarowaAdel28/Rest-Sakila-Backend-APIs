package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.jets.entity.Actor} entity
 */
@XmlRootElement(name = "actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ActorFormDto implements Serializable {
    private String firstName;
    private String lastName;
}
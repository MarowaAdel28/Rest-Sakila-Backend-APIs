package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Customer} entity
 */
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoDto implements Serializable {
    private Short customerId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private Date createDate;
    private Date lastUpdate;
    private String address;
}
package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.Staff} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class StaffDto implements Serializable {
    private Short staffId;
    private String firstName;
    private String lastName;
//    private byte[] picture;
    private String email;
    private boolean active;
    private String username;
//    private String password;
    private Date lastUpdate;
    private String address;
    private Short storeID;
}
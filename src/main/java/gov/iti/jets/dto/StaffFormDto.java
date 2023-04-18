package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement
public class StaffFormDto implements Serializable {

    private String firstName;
    private String lastName;
    private byte[] picture;

    private String email;
    private boolean active;
    private String username;
    private String password;

    private String address;
    private String district;
    private Short city;
    private Short country;
    private String postalCode;
    private String phone;

    private Short storeID;
}

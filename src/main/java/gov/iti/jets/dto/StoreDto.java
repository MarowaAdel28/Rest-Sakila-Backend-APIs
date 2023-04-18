package gov.iti.jets.dto;

import gov.iti.jets.entity.Address;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement
public class StoreDto implements Serializable {

    private Short storeId;
    private Date lastUpdate;
    private String storeAddress;
    private String managerName;
}

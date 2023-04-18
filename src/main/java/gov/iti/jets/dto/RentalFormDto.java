package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement
public class RentalFormDto {

    private Short customer;
    private Short inventory;
    private Short staff;
    private BigDecimal amount;
    private int returnDt;
}

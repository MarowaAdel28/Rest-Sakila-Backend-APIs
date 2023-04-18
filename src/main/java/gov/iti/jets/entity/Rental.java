/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

/**
 *
 * @author marwa
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "rental")
@NamedQueries({
    @NamedQuery(name = "Rental.findAll", query = "SELECT r FROM Rental r")
    , @NamedQuery(name = "Rental.findByRentalId", query = "SELECT r FROM Rental r WHERE r.rentalId = :rentalId")
    , @NamedQuery(name = "Rental.findByRentalDate", query = "SELECT r FROM Rental r WHERE r.rentalDate = :rentalDate")
    , @NamedQuery(name = "Rental.findByReturnDate", query = "SELECT r FROM Rental r WHERE r.returnDate = :returnDate")
    , @NamedQuery(name = "Rental.findByLastUpdate", query = "SELECT r FROM Rental r WHERE r.lastUpdate = :lastUpdate")})
public class Rental extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rental_id")
    private Integer rentalId;

    @Basic(optional = false)
    @Column(name = "rental_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentalDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer customerId;

    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory inventoryId;

    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Staff staffId;

    @OneToMany(mappedBy = "rentalId", fetch = FetchType.LAZY)
    private List<Payment> paymentList = new ArrayList<>();

}

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
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "staff")
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s")
    , @NamedQuery(name = "Staff.findByStaffId", query = "SELECT s FROM Staff s WHERE s.staffId = :staffId")
    , @NamedQuery(name = "Staff.findByFirstName", query = "SELECT s FROM Staff s WHERE s.firstName = :firstName")
    , @NamedQuery(name = "Staff.findByLastName", query = "SELECT s FROM Staff s WHERE s.lastName = :lastName")
    , @NamedQuery(name = "Staff.findByEmail", query = "SELECT s FROM Staff s WHERE s.email = :email")
    , @NamedQuery(name = "Staff.findByActive", query = "SELECT s FROM Staff s WHERE s.active = :active")
    , @NamedQuery(name = "Staff.findByUsername", query = "SELECT s FROM Staff s WHERE s.username = :username")
    , @NamedQuery(name = "Staff.findByPassword", query = "SELECT s FROM Staff s WHERE s.password = :password")
    , @NamedQuery(name = "Staff.findByLastUpdate", query = "SELECT s FROM Staff s WHERE s.lastUpdate = :lastUpdate")})
public class Staff extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "staff_id")
    private Short staffId;

    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;

    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;

    @Basic(optional = false)
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Address addressId;

    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Store storeId;

    @OneToOne(mappedBy = "managerStaffId", fetch = FetchType.LAZY)
    private Store store;

    @OneToMany(mappedBy = "staffId", fetch = FetchType.LAZY)
    private List<Rental> rentalList = new ArrayList<>();

    @OneToMany(mappedBy = "staffId", fetch = FetchType.LAZY)
    private List<Payment> paymentList = new ArrayList<>();

}

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
@Table(name = "inventory")
@NamedQueries({
    @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
    , @NamedQuery(name = "Inventory.findByInventoryId", query = "SELECT i FROM Inventory i WHERE i.inventoryId = :inventoryId")
    , @NamedQuery(name = "Inventory.findByLastUpdate", query = "SELECT i FROM Inventory i WHERE i.lastUpdate = :lastUpdate")})
public class Inventory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Film filmId;

    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Store storeId;

    @OneToMany(mappedBy = "inventoryId", fetch = FetchType.LAZY)
    private List<Rental> rentalList = new ArrayList<>();

}

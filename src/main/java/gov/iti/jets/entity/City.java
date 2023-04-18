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
@Table(name = "city")
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
    , @NamedQuery(name = "City.findByCityId", query = "SELECT c FROM City c WHERE c.cityId = :cityId")
    , @NamedQuery(name = "City.findByCity", query = "SELECT c FROM City c WHERE c.city = :city")
    , @NamedQuery(name = "City.findByLastUpdate", query = "SELECT c FROM City c WHERE c.lastUpdate = :lastUpdate")})
public class City extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "city_id")
    private Short cityId;

    @Basic(optional = false)
    @Column(name = "city")
    private String city;

    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "cityId", fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList<>();

    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country countryId;
}

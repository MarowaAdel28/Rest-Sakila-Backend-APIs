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
@Table(name = "language")
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")
    , @NamedQuery(name = "Language.findByLanguageId", query = "SELECT l FROM Language l WHERE l.languageId = :languageId")
    , @NamedQuery(name = "Language.findByName", query = "SELECT l FROM Language l WHERE l.name = :name")
    , @NamedQuery(name = "Language.findByLastUpdate", query = "SELECT l FROM Language l WHERE l.lastUpdate = :lastUpdate")})
public class Language extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "language_id")
    private Short languageId;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "languageId", fetch = FetchType.LAZY)
    private List<Film> filmList = new ArrayList<>();

    @OneToMany(mappedBy = "originalLanguageId", fetch = FetchType.LAZY)
    private List<Film> filmList1;

}

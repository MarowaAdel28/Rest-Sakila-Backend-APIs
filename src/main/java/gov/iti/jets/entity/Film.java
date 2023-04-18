/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import jakarta.persistence.Lob;
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
@Table(name = "film")
@NamedQueries({
    @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
    , @NamedQuery(name = "Film.findByFilmId", query = "SELECT f FROM Film f WHERE f.filmId = :filmId")
    , @NamedQuery(name = "Film.findByTitle", query = "SELECT f FROM Film f WHERE f.title = :title")
    , @NamedQuery(name = "Film.findByReleaseYear", query = "SELECT f FROM Film f WHERE f.releaseYear = :releaseYear")
    , @NamedQuery(name = "Film.findByRentalDuration", query = "SELECT f FROM Film f WHERE f.rentalDuration = :rentalDuration")
    , @NamedQuery(name = "Film.findByRentalRate", query = "SELECT f FROM Film f WHERE f.rentalRate = :rentalRate")
    , @NamedQuery(name = "Film.findByLength", query = "SELECT f FROM Film f WHERE f.length = :length")
    , @NamedQuery(name = "Film.findByReplacementCost", query = "SELECT f FROM Film f WHERE f.replacementCost = :replacementCost")
    , @NamedQuery(name = "Film.findByRating", query = "SELECT f FROM Film f WHERE f.rating = :rating")
    , @NamedQuery(name = "Film.findBySpecialFeatures", query = "SELECT f FROM Film f WHERE f.specialFeatures = :specialFeatures")
    , @NamedQuery(name = "Film.findByLastUpdate", query = "SELECT f FROM Film f WHERE f.lastUpdate = :lastUpdate")})
public class Film extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "film_id")
    private Short filmId;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;
//    @Temporal(TemporalType.DATE)
//    private Date releaseYear;

    @Basic(optional = false)
    @Column(name = "rental_duration")
    private short rentalDuration;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Basic(optional = false)
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Basic(optional = false)
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<FilmCategory> filmCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<FilmActor> filmActorList = new ArrayList<>();

    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Language languageId;

    @JoinColumn(name = "original_language_id", referencedColumnName = "language_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Language originalLanguageId;

    @OneToMany(mappedBy = "filmId", fetch = FetchType.LAZY)
    private List<Inventory> inventoryList = new ArrayList<>();

}

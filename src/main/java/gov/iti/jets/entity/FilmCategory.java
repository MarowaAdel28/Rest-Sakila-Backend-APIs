/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "film_category")
@NamedQueries({
    @NamedQuery(name = "FilmCategory.findAll", query = "SELECT f FROM FilmCategory f")
    , @NamedQuery(name = "FilmCategory.findByFilmId", query = "SELECT f FROM FilmCategory f WHERE f.filmCategoryPK.filmId = :filmId")
    , @NamedQuery(name = "FilmCategory.findByCategoryId", query = "SELECT f FROM FilmCategory f WHERE f.filmCategoryPK.categoryId = :categoryId")
    , @NamedQuery(name = "FilmCategory.findByLastUpdate", query = "SELECT f FROM FilmCategory f WHERE f.lastUpdate = :lastUpdate")})
public class FilmCategory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected FilmCategoryPK filmCategoryPK;

    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Film film;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
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
@Table(name = "film_text")
@NamedQueries({
    @NamedQuery(name = "FilmText.findAll", query = "SELECT f FROM FilmText f")
    , @NamedQuery(name = "FilmText.findByFilmId", query = "SELECT f FROM FilmText f WHERE f.filmId = :filmId")
    , @NamedQuery(name = "FilmText.findByTitle", query = "SELECT f FROM FilmText f WHERE f.title = :title")})
public class FilmText extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "film_id")
    private Short filmId;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    public FilmText(String title, String description) {
        this.title = title;
        this.description = description;
    }
}

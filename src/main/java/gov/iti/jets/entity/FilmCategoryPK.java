/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Embeddable
public class FilmCategoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "film_id")
    private short filmId;

    @Basic(optional = false)
    @Column(name = "category_id")
    private short categoryId;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) filmId;
        hash += (int) categoryId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FilmCategoryPK)) {
            return false;
        }
        FilmCategoryPK other = (FilmCategoryPK) object;
        if (this.filmId != other.filmId) {
            return false;
        }
        if (this.categoryId != other.categoryId) {
            return false;
        }
        return true;
    }
}

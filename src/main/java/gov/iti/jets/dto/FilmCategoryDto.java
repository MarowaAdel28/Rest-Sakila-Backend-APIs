package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.FilmCategory} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class FilmCategoryDto implements Serializable {
    private FilmCategoryPKDto filmCategoryPK;
    private Date lastUpdate;

    /**
     * A DTO for the {@link gov.iti.jets.entity.FilmCategoryPK} entity
     */
    @Data
    public static class FilmCategoryPKDto implements Serializable {
        private final short filmId;
        private final short categoryId;
    }
}
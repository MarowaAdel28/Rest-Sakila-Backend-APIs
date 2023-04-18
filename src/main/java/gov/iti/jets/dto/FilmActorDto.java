package gov.iti.jets.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link gov.iti.jets.entity.FilmActor} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class FilmActorDto implements Serializable {
    private FilmActorPKDto filmActorPK;
    private Date lastUpdate;

    /**
     * A DTO for the {@link gov.iti.jets.entity.FilmActorPK} entity
     */
    @Data
    public static class FilmActorPKDto implements Serializable {
        private final short actorId;
        private final short filmId;
    }
}
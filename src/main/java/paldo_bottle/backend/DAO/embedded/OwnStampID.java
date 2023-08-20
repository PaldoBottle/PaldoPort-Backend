package paldo_bottle.backend.DAO.embedded;

import lombok.*;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class OwnStampID implements Serializable {
    private RegionID location;
    private String   userId;
}

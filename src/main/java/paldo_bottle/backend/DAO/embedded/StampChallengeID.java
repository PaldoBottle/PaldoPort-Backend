package paldo_bottle.backend.DAO.embedded;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StampChallengeID implements Serializable {
    private String challengeName;
    private RegionID location;
}

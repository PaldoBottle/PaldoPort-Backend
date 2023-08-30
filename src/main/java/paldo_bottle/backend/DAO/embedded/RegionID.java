package paldo_bottle.backend.DAO.embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class RegionID implements Serializable {
    @Column(name = "supDistrict", length = 200, nullable = false)
    private String supDistrict;
    @Column(name = "district", length = 200, nullable = false)
    private String district;
}

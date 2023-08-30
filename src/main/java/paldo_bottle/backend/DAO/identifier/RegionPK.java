package paldo_bottle.backend.DAO.identifier;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegionPK implements Serializable{
    private String supDistrict;
    private String district;
}
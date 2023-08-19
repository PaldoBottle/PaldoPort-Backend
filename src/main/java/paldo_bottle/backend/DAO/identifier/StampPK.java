package paldo_bottle.backend.DAO.identifier;

import lombok.*;
import paldo_bottle.backend.DAO.Region;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StampPK implements Serializable{
    private Region region;
}
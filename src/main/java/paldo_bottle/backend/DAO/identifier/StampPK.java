package paldo_bottle.backend.DAO.identifier;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.Region;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StampPK implements Serializable{
    private Region region;
}
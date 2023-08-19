package paldo_bottle.backend.DAO.identifier;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.User;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OwnStampPK implements Serializable {
    private User    userId;
    private Stamp   stamp;
//    private String  supDistrict;
//    private String  district;
}

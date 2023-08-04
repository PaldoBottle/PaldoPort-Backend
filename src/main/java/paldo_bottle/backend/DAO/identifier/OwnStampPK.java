package paldo_bottle.backend.DAO.identifier;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OwnStampPK implements Serializable {
    private String  userId;
    private String  stampId;
}

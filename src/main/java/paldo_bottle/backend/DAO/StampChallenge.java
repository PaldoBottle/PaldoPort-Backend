package paldo_bottle.backend.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.StampChallengePK;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(StampChallengePK.class)
public class StampChallenge {
    @Id
    private String  challengeName;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
            @JoinColumn(name = "district", referencedColumnName = "district")
    })
    private Stamp stamp;
}

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
    @Column(name = "supDistrict", length = 200, nullable = false, insertable=false, updatable = false)
    private String supDistrict;

    @Id
    @Column(name = "district", length = 200, nullable = false, insertable=false, updatable = false)
    private String district;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
            @JoinColumn(name = "district", referencedColumnName = "district")
    })
    private Stamp stamp;
}

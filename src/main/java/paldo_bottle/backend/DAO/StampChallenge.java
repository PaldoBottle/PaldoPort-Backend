package paldo_bottle.backend.DAO;

import lombok.*;
import paldo_bottle.backend.DAO.embedded.OwnStampID;
import paldo_bottle.backend.DAO.embedded.StampChallengeID;
import paldo_bottle.backend.DAO.identifier.StampChallengePK;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Setter
public class StampChallenge {
    @EmbeddedId
    private StampChallengeID id;

    @MapsId("challengeName")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeName")
    private Challenge challenge;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "supDistrict"),
            @JoinColumn(name = "district")
    })
    @MapsId("location")
    private Stamp stamp;

    static public StampChallenge createStampChallenge(Challenge challenge, Stamp stamp) {
        StampChallenge stampChallenge = new StampChallenge();
        stampChallenge.id = new StampChallengeID(
                challenge.getName(),
                stamp.getLocation()
        );
        stampChallenge.setStamp(stamp);
        stampChallenge.setChallenge(challenge);
        return stampChallenge;
    }

}

package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.RegionPK;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(RegionPK.class)
public class Stamp {

    @Id
    @Column(name = "supDistrict", length = 200, nullable = false)
    private String supDistrict;

    @Id
    @Column(name = "district", length = 200, nullable = false)
    private String district;

    @Column(name = "point")
    private Long point;

    @OneToOne(mappedBy = "stamp")
    private Region region;

//    @OneToMany(mappedBy = "stamp")
//    private List<StampChallenge>    challengeList;
}

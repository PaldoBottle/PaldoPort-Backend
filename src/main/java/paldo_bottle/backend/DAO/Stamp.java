package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.RegionPK;
import paldo_bottle.backend.DAO.identifier.StampPK;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(StampPK.class)
public class Stamp {
    @Id
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "supDistrict"),
            @JoinColumn(name = "district")
    })
    private Region region;

    @Column(name = "point")
    private Long point;

    @OneToMany(mappedBy = "stamp")
    private List<OwnStamp> owners = new ArrayList<>();
}

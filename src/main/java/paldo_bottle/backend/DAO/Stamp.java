package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
            @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
            @JoinColumn(name = "district", referencedColumnName = "district")
    })
    private Region region;

    @Column(name = "point")
    private Long point;

    @OneToMany(mappedBy = "stamp")
    private List<OwnStamp> owners = new ArrayList<>();

    @Column()
    @ColumnDefault(value = "0")
    private Long published;

    public Stamp(Long point) {
        this.point = point;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void addOwner(OwnStamp owner) {
        owners.add(owner);
        this.published += 1;
        owner.setStamp(this);
    }
}

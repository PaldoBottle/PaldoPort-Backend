package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import paldo_bottle.backend.DAO.embedded.RegionID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Stamp {
    @EmbeddedId
    private RegionID location;

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

    @Column()
    @ColumnDefault(value = "0")
    private Long published = 0L;

    public Stamp(Long point) {
        this.point = point;
    }

    public void setRegion(Region region) {
        this.location = new RegionID(
            region.getLocation().getSupDistrict(),
            region.getLocation().getDistrict()
        );
        this.region = region;
    }

    public void addOwner(OwnStamp owner) {
        owners.add(owner);
        this.published += 1;
    }
}

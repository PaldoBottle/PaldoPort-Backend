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

    // == 생성 메서드 == //
    static public Stamp createStamp(String supDistrict, String district,
                              String region_description, Long stamp_point)
    {
        Region region = Region.builder()
                .supDistrict(supDistrict)
                .district(district)
                .description(region_description)
                .build();
        Stamp stamp = new Stamp();
        stamp.setRegion(region);
        stamp.region = region;
        return stamp;
    }

    // == 연관관계 메서드 == //
    public void setRegion(Region region) {
        region.setStamp(this);
        this.region = region;
    }
}

package paldo_bottle.backend.DAO;

import lombok.*;
import paldo_bottle.backend.DAO.embedded.RegionID;
import paldo_bottle.backend.DAO.identifier.RegionPK;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "region")
//@IdClass(RegionPK.class)
public class Region {
//    @Id
//    @Column(name = "supDistrict", length = 200, nullable = false)
//    private String supDistrict;
//
//    @Id
//    @Column(name = "district", length = 200, nullable = false)
//    private String district;
    @EmbeddedId
    private RegionID location;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "region")
    private Stamp stamp;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Landmark> landmarkList = new ArrayList<>();

    @Builder
    public Region(String supDistrict, String district, String description){
        this.location = new RegionID(supDistrict, district);
        this.description = description;
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
    }
}
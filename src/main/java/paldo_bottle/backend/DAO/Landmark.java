package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.LandmarkPK;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Landmark {
    @Id
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String  description;

    @Column(name = "supDistrict", length = 200, nullable = false, insertable=false, updatable = false)
    private String supDistrict;

    @Column(name = "district", length = 200, nullable = false, insertable=false, updatable = false)
    private String district;
    //M:1 EntireLecture
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
            @JoinColumn(name = "district", referencedColumnName = "district")
    })
    private Region region;

    public void setRegion(Region region) {
        this.region = region;
    }
}

